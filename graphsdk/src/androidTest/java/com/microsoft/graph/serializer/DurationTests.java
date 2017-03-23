package com.microsoft.graph.serializer;

import android.test.AndroidTestCase;

import org.apache.xerces.jaxp.datatype.DatatypeFactoryImpl;

import javax.xml.datatype.Duration;

public class DurationTests extends AndroidTestCase {

    public void testDurationSerializer() throws Exception {
        String strDuration = DatatypeFactoryImpl.newInstance().newDurationDayTime(true, 0, 2, 30, 0).toString();
        assertEquals("P0DT2H30M0S", strDuration);
    }

    public void testDurationDeserializer() throws Exception {
        Duration duration = DatatypeFactoryImpl.newInstance().newDurationDayTime(true, 0, 1, 30, 45);
        assertEquals(0, duration.getMonths());
        assertEquals(0, duration.getDays());
        assertEquals(1, duration.getHours());
        assertEquals(30, duration.getMinutes());
        assertEquals(45, duration.getSeconds());
    }
}
