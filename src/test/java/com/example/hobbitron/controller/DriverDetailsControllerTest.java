package com.example.hobbitron.controller;

import com.example.hobbitron.model.DriverDetails;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DriverDetailsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /driverdetails -> HTTP 200")
    void getAllGet200() throws Exception {
        mockMvc
                .perform(get("/driverdetails"))
                .andExpect(status().is(200));
    }

    @Test
    void getNonExistingEndpoint404() throws Exception {
        mockMvc
                .perform(get("/nonexistinendpoint"))
                .andExpect(status().is(404));
    }

    @Test
    void given3DriversDetailsInDB_whenGETDriversDetails_thenHTTP200AndListSize3() throws Exception {
        final var mvcResult = mockMvc
                .perform(get("/driverdetails"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        // get JSON from the response
        final var driversFromDbInJSONFormat = mvcResult.getResponse().getContentAsString();
        // map JSON -> Java
        List<DriverDetails> driverDetailsFromDB = objectMapper.readValue(driversFromDbInJSONFormat, new TypeReference<>(){});

        assertEquals(4, driverDetailsFromDB.size());
    }

    @Test
    @DirtiesContext
    void givenFullSpringContextWithDbInitialized_whenPOSTDriverDetailsWithJSON_thenHTTP200AndDriverWithId() throws Exception {

        var prefix= "Mr";
        var firstName = "Alex";
        var lastName = "O'Neill";
        var telephoneNumber = "0123456789";
        var addressLine1 = "126 Main Street";
        var addressLine2 = "Main Road";
        var city = "Armagh";
        var postcode = "BT61 5GW";
        var vehicleBodyType = "Estate";
        var engineSize = "2000";
        var additionalDrivers = "1";
        var commercialUse = "no";
        var outsideStateUse = "no";
        var currentValue = "35000";
        var dateRegistered = "05/12/2008";
        var driver1 = new DriverDetails(null, prefix, firstName,lastName, telephoneNumber, addressLine1, addressLine2, city, postcode, vehicleBodyType, engineSize, additionalDrivers, commercialUse, outsideStateUse, currentValue, dateRegistered);
        var driver1AsString = objectMapper.writeValueAsString(driver1);

        final var mvcResult = mockMvc
                .perform(
                        post("/driverdetails")
                        .content(driver1AsString)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        final var driverFromDbAsJson = mvcResult.getResponse().getContentAsString();

        DriverDetails driverDetailsFromDB = objectMapper.readValue(driverFromDbAsJson, DriverDetails.class);

        assertAll(
                () -> assertNotNull(driverDetailsFromDB.getId()),
                () -> assertEquals(firstName, driverDetailsFromDB.getFirstName()),
                () -> assertEquals(lastName, driverDetailsFromDB.getLastName())
        );
    }

}