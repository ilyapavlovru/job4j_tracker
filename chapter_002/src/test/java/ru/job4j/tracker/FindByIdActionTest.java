package ru.job4j.tracker;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByIdActionTest {

    @Test
    public void whenIdIsPresentThenItemIsFound() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        tracker.add(new Item("Found item"));
        FindByIdAction find = new FindByIdAction(out);

        Input input = mock(Input.class);

        when(input.askStr("Enter id to find items: ")).thenReturn(tracker.findAll().get(0).getId());

        find.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), CoreMatchers.is("id: " + tracker.findAll().get(0).getId() + "; name: " + tracker.findAll().get(0).getName() + ln));
    }

    @Test
    public void whenIdIsAbsentThenItemIsNotFound() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        tracker.add(new Item("Not founded item"));
        FindByIdAction find = new FindByIdAction(out);

        Input input = mock(Input.class);

        find.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), CoreMatchers.is("Not found" + ln));
    }
}