package edu.whu.demo;

import com.alibaba.fastjson.JSON;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@WebMvcTest(cmsController.class)
class cmsControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private cmsController cmsc;
    @Test
    void addCommodity() throws Exception {
        commodity comm = new commodity();
        comm.setId(1L);
        comm.setName("Coke");
        comm.setPrice(10L);
        comm.setNum(10L);
        Mockito.when(cmsc.addCommodity(comm)).thenReturn(ResponseEntity.ok(comm));
        String result = mvc.perform(MockMvcRequestBuilders.post("/commodities").content(JSON.toJSONString(comm)).contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        assertEquals(result,"{\"id\":1,\"name\":\"Coke\",\"price\":10,\"num\":10}");
    }

    @Test
    void deleteCommodity() throws Exception {
        Mockito.when(cmsc.deleteCommodity(1)).thenReturn(ResponseEntity.ok().build());
        int result = mvc.perform(MockMvcRequestBuilders.delete("/commodities/1"))
                .andReturn().getResponse().getStatus();
        assertEquals(result,200);
    }

    @Test
    void updateCommodity() throws Exception {
        commodity comm = new commodity();
        comm.setId(1L);
        comm.setName("Coke");
        comm.setPrice(10L);
        comm.setNum(10L);
        Mockito.when(cmsc.addCommodity(comm)).thenReturn(ResponseEntity.ok(comm));
        Mockito.when(cmsc.updateCommodity(1,comm)).thenReturn(ResponseEntity.ok().build());
        Mockito.when(cmsc.getCommodity(1)).thenReturn(ResponseEntity.ok(comm));
        mvc.perform(MockMvcRequestBuilders.post("/commodities").content(JSON.toJSONString(comm)).contentType(MediaType.APPLICATION_JSON));
        comm.setNum(0L);
        mvc.perform(MockMvcRequestBuilders.post("/commodities/1").content(JSON.toJSONString(comm)).contentType(MediaType.APPLICATION_JSON));
        String result = mvc.perform(MockMvcRequestBuilders.get("/commodities/1")).andReturn().getResponse().getContentAsString();
        assertEquals(result,"{\"id\":1,\"name\":\"Coke\",\"price\":10,\"num\":0}");
    }
    @Test
    void getCommodity() throws Exception {
        commodity comm = new commodity();
        comm.setId(1L);
        comm.setName("Coke");
        comm.setPrice(10L);
        comm.setNum(10L);
        Mockito.when(cmsc.addCommodity(comm)).thenReturn(ResponseEntity.ok(comm));
        Mockito.when(cmsc.getCommodity(1)).thenReturn(ResponseEntity.ok(comm));
        mvc.perform(MockMvcRequestBuilders.post("/commodities").content(JSON.toJSONString(comm)).contentType(MediaType.APPLICATION_JSON));
        String result = mvc.perform(MockMvcRequestBuilders.get("/commodities/1")).andReturn().getResponse().getContentAsString();
        assertEquals(result,"{\"id\":1,\"name\":\"Coke\",\"price\":10,\"num\":10}");
    }

    @Test
    void findEmptyCommodity() throws Exception {
        commodity comm = new commodity();
        comm.setId(1L);
        comm.setName("Coke");
        comm.setPrice(10L);
        comm.setNum(0L);
        Mockito.when(cmsc.addCommodity(comm)).thenReturn(ResponseEntity.ok(comm));
        List<commodity> commList=new ArrayList<commodity>();
        commList.add(comm);
        Mockito.when(cmsc.findEmptyCommodity()).thenReturn(ResponseEntity.ok(commList));
        mvc.perform(MockMvcRequestBuilders.post("/commodities").content(JSON.toJSONString(comm)).contentType(MediaType.APPLICATION_JSON));
        String result = mvc.perform(MockMvcRequestBuilders.get("/commodities")).andReturn().getResponse().getContentAsString();
        assertEquals(result,"[{\"id\":1,\"name\":\"Coke\",\"price\":10,\"num\":0}]");
    }
}