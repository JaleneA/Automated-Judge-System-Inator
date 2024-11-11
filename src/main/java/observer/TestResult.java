package observer;

public class TestResult {
    private final String testClassName;
    private final boolean passed;

    public TestResult(String testClassName, boolean passed) {
        this.testClassName = testClassName;
        this.passed = passed;
    }

    public String getTestClassName() {
        return testClassName;
    }

    public boolean isPassed() {
        return passed;
    }
}