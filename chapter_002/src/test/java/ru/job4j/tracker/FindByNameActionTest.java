package ru.job4j.tracker;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByNameActionTest {
    @Test
    public void whenCheckOutput() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = new Item("fix bug");
        tracker.add(item);
        FindByNameAction act = new FindByNameAction(out);
        String[] answers = {
                item.getName()
        };
        act.execute(new StubInput(answers), tracker);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("id: " + item.getId() + "; name: " + item.getName())
                .toString();
        assertThat(out.toString(), is(expect));
    }

    @Test
    public void whenIdIsPresentThenItemIsFound() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        tracker.add(new Item("Found item"));
        FindByNameAction find = new FindByNameAction(out);

        Input input = mock(Input.class);

        when(input.askStr("Enter name to find items: ")).thenReturn(tracker.findAll().get(0).getName());

        find.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), CoreMatchers.is("id: " + tracker.findAll().get(0).getId() + "; name: " + tracker.findAll().get(0).getName() + ln));
    }

    @Test
    public void whenIdIsAbsentThenItemIsNotFound() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        tracker.add(new Item("Not founded item"));
        FindByNameAction find = new FindByNameAction(out);

        Input input = mock(Input.class);

        find.execute(input, tracker);

        assertThat(out.toString(), CoreMatchers.is(""));
    }
}
