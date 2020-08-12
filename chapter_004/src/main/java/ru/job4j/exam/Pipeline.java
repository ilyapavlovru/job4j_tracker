package ru.job4j.exam;

import java.util.ArrayList;
import java.util.List;

interface Step<T, U> {
    U execute(T input);
}

class StepOne implements Step<Integer, Integer> {
    @Override
    public Integer execute(Integer input) {
        return input + 100;
    }
}


class StepTwo implements Step<Integer, Integer> {
    @Override
    public Integer execute(Integer input) {
        return input + 500;
    }
}


class StepThree implements Step<Integer, String> {
    @Override
    public String execute(Integer input) {
        return "The final amount is " + input;
    }
}

public class Pipeline {
    private List<Step> pipelineSteps = new ArrayList<>();
    private Object firstStepInput = 100;

    public void addStep(Step step) {
        pipelineSteps.add(step);
    }

    public void execute() {
        for (Step step : pipelineSteps) {
            Object out = step.execute(firstStepInput);
            firstStepInput = out;
        }
    }
}

class Main {
    public static void main(String[] args) {
        Pipeline pipeline = new Pipeline();
        pipeline.addStep(new StepOne());
        pipeline.addStep(new StepTwo());
        pipeline.addStep(new StepThree());

        pipeline.execute();
    }
}