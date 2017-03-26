package pl.edu.pjwstk.lab4;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import static org.jbehave.core.io.CodeLocations.*;

public class AlarmStory extends JUnitStory {

    @Override
    public Configuration configuration() {
        MostUsefulConfiguration config = new MostUsefulConfiguration();
        config.useStoryReporterBuilder(loadStoryReporterBuilder());
        return config;
    }

    private StoryReporterBuilder loadStoryReporterBuilder() {
        StoryReporterBuilder storyReporterBuilder = new StoryReporterBuilder();
        storyReporterBuilder.withDefaultFormats();
        storyReporterBuilder.withFormats(Format.HTML, Format.TXT);
        return storyReporterBuilder;
    }
    @Override public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new AlarmSteps());
    }

}
