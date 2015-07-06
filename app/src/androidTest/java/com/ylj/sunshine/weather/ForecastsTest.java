package com.ylj.sunshine.weather;

import org.json.JSONArray;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ylj on 6/27/15.
 */
public class ForecastsTest {

    @Test
    public void testCreate() throws Exception {
        String jsonStr = "{\"cod\":\"200\",\"message\":0.0357," +
                "\"city\":{\"id\":0,\"name\":\"Mountain View\",\"country\":\"US\"," +
                "\"coord\":{\"lat\":37.4056,\"lon\":-122.0775}" +
                "}," +
                "\"cnt\":7," +
                "\"list\":[" +
                "{\"dt\":1435262400,\"temp\":{\"day\":28.11,\"min\":13.67,\"max\":28.11,\"night\":13.67,\"eve\":22.88,\"morn\":14.55},\"pressure\":988.75,\"humidity\":59,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":1.52,\"deg\":231,\"clouds\":0}," +
                "{\"dt\":1435348800,\"temp\":{\"day\":27.58,\"min\":12.39,\"max\":27.58,\"night\":12.39,\"eve\":21.95,\"morn\":17.39},\"pressure\":989.04,\"humidity\":62,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":1.57,\"deg\":226,\"clouds\":0}," +
                "{\"dt\":1435435200,\"temp\":{\"day\":18.21,\"min\":14.27,\"max\":20.76,\"night\":15.43,\"eve\":20.76,\"morn\":14.27},\"pressure\":1009.13,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":2.02,\"deg\":189,\"clouds\":13,\"rain\":1.11}," +
                "{\"dt\":1435521600,\"temp\":{\"day\":17.67,\"min\":13.99,\"max\":20.1,\"night\":14.05,\"eve\":20.1,\"morn\":13.99},\"pressure\":1009.81,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":2.03,\"deg\":174,\"clouds\":45,\"rain\":0.32}," +
                "{\"dt\":1435608000,\"temp\":{\"day\":17.19,\"min\":12.61,\"max\":20.24,\"night\":13.96,\"eve\":20.24,\"morn\":12.61},\"pressure\":1009.12,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":1.5,\"deg\":242,\"clouds\":11}," +
                "{\"dt\":1435694400,\"temp\":{\"day\":16.91,\"min\":11.03,\"max\":20.41,\"night\":13.72,\"eve\":20.41,\"morn\":11.03},\"pressure\":1010.69,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":1.84,\"deg\":284,\"clouds\":11}," +
                "{\"dt\":1435780800,\"temp\":{\"day\":17.85,\"min\":11.59,\"max\":21.02,\"night\":14.33,\"eve\":21.02,\"morn\":11.59},\"pressure\":1010.75,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":2.56,\"deg\":297,\"clouds\":10}]}";
        Forecasts forecasts = Forecasts.Create(jsonStr);
        assertEquals(forecasts.getLocation().getCode(), 200);
        assertEquals(forecasts.getCnt(),7);

    }
}