package com.microsoft.graph.functional;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.Suppress;

//import com.microsoft.graph.extensions.IDirectoryDeletedItemsCollectionPage;
import com.microsoft.graph.extensions.*;

import org.junit.Assert;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Suppress
public class OutlookTests extends AndroidTestCase {

    public void testQueryDeletedItems() {
        TestBase testBase = new TestBase();
        //IDirectoryDeletedItemsCollectionPage deletedItems = testBase.graphClient.getDirectory().getDeletedItemsByType("microsoft.graph.group").buildRequest().get();
        //assertNotNull(deletedItems);
    }

    public void testSendMail() {
        TestBase testBase = new TestBase();
        User me = testBase.graphClient.getMe().buildRequest().get();
        Recipient r = new Recipient();
        EmailAddress address = new EmailAddress();
        address.address = me.mail;
        r.emailAddress = address;
        Message message = new Message();
        message.subject = "Test E-Mail";
        message.from = r;
        ArrayList<Recipient> recipients = new ArrayList<Recipient>();
        recipients.add(r);
        message.toRecipients = recipients;
        testBase.graphClient.getMe().getSendMail(message, true).buildRequest().post();
    }

    public void testGetFindMeetingTimes() {
        TestBase testBase = new TestBase();
        List<AttendeeBase> attendees = new ArrayList<>();
        AttendeeBase attendeeBase = new AttendeeBase();
        EmailAddress email = new EmailAddress();
        email.address = "katiej@mod810997.onmicrosoft.com";
        attendeeBase.emailAddress = email;
        attendees.add(attendeeBase);
        try {
            Duration duration = DatatypeFactory.newInstance().newDuration("PT30M");
            MeetingTimeSuggestionsResult result = testBase.graphClient.getMe().getFindMeetingTimes(attendees, null, null, duration, 10, true, false, 10.0).buildRequest().post();
            assertNotNull(result);
        } catch (Exception e) {
            Assert.fail("Duration could not be created from String");
        }

    }
}