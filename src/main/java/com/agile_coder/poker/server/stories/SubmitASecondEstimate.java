/*
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2011 the original author or authors.
 */
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

import com.agile_coder.poker.server.stories.steps.SubmitASecondEstimateSteps;

public class SubmitASecondEstimate extends JUnitStory {

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
        return new InstanceStepsFactory(configuration(), new SubmitASecondEstimateSteps())
                .createCandidateSteps();
    }

}
