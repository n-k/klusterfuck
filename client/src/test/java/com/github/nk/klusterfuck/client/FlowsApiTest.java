/*
 * API
 * API
 *
 * OpenAPI spec version: v1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.github.nk.klusterfuck.client;

import com.github.nk.klusterfuck.ApiException;
import com.github.nk.klusterfuck.client.model.CreateFlowRequest;
import com.github.nk.klusterfuck.client.model.DAGStepRef;
import com.github.nk.klusterfuck.client.model.Flow;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for FlowsApi
 */
@Ignore
public class FlowsApiTest {

    private final FlowsApi api = new FlowsApi();

    
    /**
     * create
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createTest() throws ApiException {
        CreateFlowRequest body = null;
        Flow response = api.create(body);

        // TODO: test validations
    }
    
    /**
     * delete
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteTest() throws ApiException {
        String id = null;
        String response = api.delete(id);

        // TODO: test validations
    }
    
    /**
     * deploy
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deployTest() throws ApiException {
        String id = null;
        Flow response = api.deploy(id);

        // TODO: test validations
    }
    
    /**
     * get
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getTest() throws ApiException {
        String id = null;
        Flow response = api.get(id);

        // TODO: test validations
    }
    
    /**
     * getModel
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getModelTest() throws ApiException {
        String id = null;
        DAGStepRef response = api.getModel(id);

        // TODO: test validations
    }
    
    /**
     * list
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void listTest() throws ApiException {
        List<Flow> response = api.list();

        // TODO: test validations
    }
    
    /**
     * saveModel
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void saveModelTest() throws ApiException {
        String id = null;
        Object body = null;
        String response = api.saveModel(id, body);

        // TODO: test validations
    }
    
    /**
     * validate
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void validateTest() throws ApiException {
        Object body = null;
        String response = api.validate(body);

        // TODO: test validations
    }
    
}
