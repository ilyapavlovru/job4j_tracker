package ru.job4j.tracker;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteActionTest {

    @Test
    public void whenIdIsPresentThenItemIsDeleted() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        tracker.add(new Item("Item to delete"));
        DeleteAction del = new DeleteAction(out);

        Input input = mock(Input.class);

        when(input.askStr("Enter id to delete item: ")).thenReturn(tracker.findAll().get(0).getId());

        del.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), CoreMatchers.is("Successfully deleted item" + ln));
    }

    @Test
    public void whenIdIsAbsentThenItemIsNotDeleted() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        tracker.add(new Item("Item won't be deleted"));
        DeleteAction del = new DeleteAction(out);

        Input input = mock(Input.class);

        del.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), CoreMatchers.is("Not found id to delete item" + ln));
    }
}