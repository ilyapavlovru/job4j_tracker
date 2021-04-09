package ru.job4j.tracker;

import org.junit.Test;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FindAllActionTest {
    @Test
    public void whenCheckOutput() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = new Item("fix bug");
        tracker.add(item);
        ShowAction act = new ShowAction(out);
        act.execute(new StubInput(new String[] {}), tracker);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("id: " + item.getId() + "; name: " + item.getName())
                .toString();
        assertThat(out.toString(), is(expect));
    }
}
