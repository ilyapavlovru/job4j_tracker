package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReplaceActionTest {

    @Test
    public void whenIdIsFoundThenItemIsReplaced() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        ReplaceAction rep = new ReplaceAction(out);

        Input input = mock(Input.class);

        when(input.askStr("Enter id to replace item: ")).thenReturn(tracker.findAll().get(0).getId());
        when(input.askStr("Enter new name: ")).thenReturn(replacedName);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("Successfully replaced item" + ln));
        assertThat(tracker.findAll().get(0).getName(), is(replacedName));
    }

    @Test
    public void whenIdIsNotFoundThenItemIsNotReplaced() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        ReplaceAction rep = new ReplaceAction(out);

        Input input = mock(Input.class);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("Not found id to replace item" + ln));
        assertThat(tracker.findAll().get(0).getName(), is("Replaced item"));
    }
}
