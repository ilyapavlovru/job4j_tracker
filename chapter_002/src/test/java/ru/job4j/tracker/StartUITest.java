package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {
    @Test
    public void whenExit() {
        Output out = new StubOutput();
        StubInput input = new StubInput(
                new String[] {"0"}
        );
        StubAction action = new StubAction();
        List<UserAction> actions = new ArrayList<>();
        actions.add(action);
        new StartUI(out).init(input, new Tracker(), actions);
        assertThat(action.isCall(), is(true));
    }

    @Test
    public void whenPrtMenu() {
        Output out = new StubOutput();

        StubInput input = new StubInput(
                new String[] {"0"}
        );
        StubAction action = new StubAction();
        List<UserAction> actions = new ArrayList<>();
        actions.add(action);
        new StartUI(out).init(input, new Tracker(), actions);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("Menu.")
                .add("0. Stub action")
                .toString();
        assertThat(out.toString(), is(expect));
    }

//    @Test
//    public void whenAddItem() {
//        String[] answers = {"Fix PC"};
//        Input input = new StubInput(answers);
//        Tracker tracker = new Tracker();
//        StartUI.createItem(input, tracker);
//        Item created = tracker.findAll()[0];
//        Item expected = new Item("Fix PC");
//        assertThat(created.getName(), is(expected.getName()));
//    }
//
//    @Test
//    public void whenReplaceItem() {
//        Tracker tracker = new Tracker();
//        Item item = new Item("new item");
//        tracker.add(item);
//        String[] answers = {
//                item.getId(), // id сохраненной заявки в объект tracker.
//                "replaced item"
//        };
//        StartUI.replaceItem(new StubInput(answers), tracker);
//        Item replaced = tracker.findById(item.getId());
//        assertThat(replaced.getName(), is("replaced item"));
//    }
//
//    @Test
//    public void whenDeleteItem() {
//        Tracker tracker = new Tracker();
//        Item item = new Item("new item");
//        tracker.add(item);
//        String id = item.getId();
//        String[] answers = {
//                item.getId() // id сохраненной заявки в объект tracker.
//        };
//        StartUI.deleteItem(new StubInput(answers), tracker);
//        assertThat(tracker.findById(id), Matchers.is(nullValue()));
//    }
}