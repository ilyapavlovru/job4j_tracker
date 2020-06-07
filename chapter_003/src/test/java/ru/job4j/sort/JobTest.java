package ru.job4j.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

public class JobTest {
    @Test
    public void whenComparatorByNameAndPriority() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );

        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenSortJobAscByName() {
        List<Job> jobs = Arrays.asList(
                new Job("Fix bug", 1),
                new Job("Mega task", 7),
                new Job("Fix bug", 2),
                new Job("X task", 0)
        );
        List<Job> expected = List.of(
                new Job("Fix bug", 1),
                new Job("Fix bug", 2),
                new Job("Mega task", 7),
                new Job("X task", 0)
        );
        Collections.sort(jobs, new JobAscByName());
        assertThat(jobs.toString(), is(expected.toString()));
    }

    @Test
    public void whenSortJobDescByName() {
        List<Job> jobs = Arrays.asList(
                new Job("Fix bug", 1),
                new Job("Mega task", 7),
                new Job("Fix bug", 2),
                new Job("X task", 0)
        );
        List<Job> expected = List.of(
                new Job("X task", 0),
                new Job("Mega task", 7),
                new Job("Fix bug", 1),
                new Job("Fix bug", 2)
        );
        Collections.sort(jobs, new JobDescByName());
        assertThat(jobs.toString(), is(expected.toString()));
    }

    @Test
    public void whenSortJobAscByPriority() {
        List<Job> jobs = Arrays.asList(
                new Job("Fix bug", 1),
                new Job("Mega task", 7),
                new Job("Fix bug", 2),
                new Job("X task", 0)
        );
        List<Job> expected = List.of(
                new Job("Mega task", 7),
                new Job("Fix bug", 2),
                new Job("Fix bug", 1),
                new Job("X task", 0)
        );
        Collections.sort(jobs, new JobAscByPriority());
        assertThat(jobs.toString(), is(expected.toString()));
    }

    @Test
    public void whenSortJobDescByPriority() {
        List<Job> jobs = Arrays.asList(
                new Job("Fix bug", 1),
                new Job("Mega task", 7),
                new Job("Fix bug", 2),
                new Job("X task", 0)
        );
        List<Job> expected = List.of(
                new Job("X task", 0),
                new Job("Fix bug", 1),
                new Job("Fix bug", 2),
                new Job("Mega task", 7)
        );
        Collections.sort(jobs, new JobDescByPriority());
        assertThat(jobs.toString(), is(expected.toString()));
    }

    @Test
    public void whenSortJobDescByNameThenJobDescByPriority() {
        List<Job> jobs = Arrays.asList(
                new Job("Fix bug", 1),
                new Job("Fix bug", 4),
                new Job("Fix bug", 2),
                new Job("X task", 0)
        );
        List<Job> expected = List.of(
                new Job("X task", 0),
                new Job("Fix bug", 1),
                new Job("Fix bug", 2),
                new Job("Fix bug", 4)
        );
        Collections.sort(jobs, new JobDescByName().thenComparing(new JobDescByPriority()));
        assertThat(jobs.toString(), is(expected.toString()));
    }
}