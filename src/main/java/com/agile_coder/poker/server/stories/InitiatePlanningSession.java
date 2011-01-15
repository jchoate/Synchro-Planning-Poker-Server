package com.agile_coder.poker.server.stories;

import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.reporters.StoryReporterBuilder.Format;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;

import com.agile_coder.poker.server.stories.steps.InitiatePlanningSteps;

public class InitiatePlanningSession extends JUnitStory {

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
        // where to find the stories
                .useStoryLoader(new LoadFromClasspath(this.getClass().getClassLoader()))
                // CONSOLE and TXT reporting
                .useStoryReporterBuilder(
                        new StoryReporterBuilder().withDefaultFormats().withFormats(
                                Format.IDE_CONSOLE));
    }

    @Override
    public List<CandidateSteps> candidateSteps() {
        // varargs, can have more that one steps classes
        return new InstanceStepsFactory(configuration(), new InitiatePlanningSteps())
                .createCandidateSteps();
    }
}
