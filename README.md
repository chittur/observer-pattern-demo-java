# Observer Pattern Demo - Java

An educational demonstration of the **Observer design pattern** implemented in Java. This project provides a clean, well-documented example for students learning object-oriented design patterns, modern Java development practices, and software engineering best practices including testing, code coverage, and continuous integration. This project is based on the original C# implementation at [observer-pattern-demo](https://github.com/chittur/observer-pattern-demo)

## 📚 Overview

**Software design patterns** are programming paradigms that describe reusable patterns for common design problems. They are a set of tried and tested solutions to common problems in software design. They are not algorithms or code snippets that can be copied and pasted into your code. They are more like templates that can be applied to different situations.

**Observer design pattern** is demonstrated in this project. The Observer pattern is a behavioral design pattern that lets you define a subscription mechanism to notify interested clients about any events that happen to the object they're observing. The Observer pattern provides a way to subscribe and unsubscribe to and from these events for any object that implements a subscriber interface.

### Real-World Applications

The Observer pattern is used in:

- **GUI applications** to notify the GUI of changes in the underlying data model
- **Applications involving distributed systems** to notify clients of changes in the underlying data model  
- **Applications involving event-driven programming** to notify subscribers of events
- **Model-View-Controller (MVC) architectures** where views observe model changes
- **Reactive programming** frameworks like RxJava and Spring WebFlux

## 🏗️ Design

This project defines a linked list navigator that demonstrates the Observer pattern. The navigator is initialized with some data (in this case, simply numbers). As the linked list is being navigated, any interested subscriber is notified when each node is visited, thus demonstrating the observer pattern.

### Key Components

- **`INodeNavigationListener`** - Observer interface defining the contract for receiving notifications
- **`NodeNavigator`** - Subject class that maintains observers and sends notifications
- **`Main`** - Demonstration class showing various usage scenarios
- **Comprehensive test suite** - Validates pattern implementation and edge cases

### Class Diagram

```
┌─────────────────────────────┐
│   INodeNavigationListener   │ ◄─── Observer Interface
│                             │
│ + onNodeVisited(int): void  │
└─────────────────────────────┘
                 ▲
                 │ implements
                 │
┌─────────────────────────────┐
│      NodeNavigator          │ ◄─── Subject/Observable
│                             │
│ - list: LinkedList<Integer> │
│ - listener: INode...        │
│                             │
│ + subscribe(listener): void │
│ + unsubscribe(): void       │
│ + navigate(): void          │
│ + size(): int               │
│ + isEmpty(): boolean        │
└─────────────────────────────┘
```

## 🚀 Getting Started

### Prerequisites

- **Java 17** or higher
- **Maven 3.8** or higher
- **Git** for version control

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/chittur/observer-pattern-demo-java.git
   cd observer-pattern-demo-java
   ```

2. **Compile the project:**
   ```bash
   mvn clean compile
   ```

3. **Run the tests:**
   ```bash
   mvn test
   ```

4. **Run the demonstration:**
   ```bash
   mvn exec:java
   ```

## 🔧 Build Commands

### Essential Maven Commands

| Command | Description |
|---------|-------------|
| `mvn clean compile` | Clean and compile the source code |
| `mvn test` | Run all unit tests |
| `mvn jacoco:report` | Generate code coverage report |
| `mvn checkstyle:check` | Run code style checks |
| `mvn package` | Create JAR file |
| `mvn site` | Generate project documentation |

### Quality Assurance

```bash
# Run all quality checks (what the CI pipeline does)
mvn clean compile test jacoco:report checkstyle:check

# View coverage report
open target/site/jacoco/index.html

# View checkstyle report  
open target/site/checkstyle.html
```

## 🧪 Testing

This project demonstrates comprehensive testing practices:

### Test Categories

- **Basic Functionality Tests** - Core observer pattern behavior (3 tests)
- **Observer Management Tests** - Subscription/unsubscription scenarios (4 tests)
- **Error Handling Tests** - Edge cases and error conditions (6 tests)
- **Navigation State Tests** - State management and multiple operations (3 tests)

**Total: Comprehensive JUnit 5 tests**

### Test Features

- **JUnit 5** with modern testing practices
- **Parameterized tests** for testing multiple scenarios
- **Nested test classes** for logical organization
- **Comprehensive assertions** with clear error messages
- **90%+ code coverage** with branch coverage tracking

### Running Specific Tests

```bash
# Run specific test class
mvn test -Dtest=NodeNavigatorTest

# Run specific test method
mvn test -Dtest=NodeNavigatorTest#testNavigationCallbacks

# Run tests with coverage
mvn test jacoco:report
```

## 📊 Code Quality

### Coverage Requirements

- **Line Coverage**: Target 100%
- **Branch Coverage**: Target 100%
- Coverage reports available at `target/site/jacoco/index.html`

### Code Style

- **Checkstyle** configuration enforces consistent coding standards
- **Javadoc** required for all public methods and classes
- **Final parameters** and variables where appropriate
- **Proper exception handling** with meaningful messages

### Checkstyle Rules

Key rules enforced:
- Max line length: 120 characters
- Proper indentation (4 spaces)
- Complete Javadoc for public APIs
- Consistent naming conventions
- Import organization
- No unused imports or variables

## 🏭 Continuous Integration

The project includes a complete CI/CD pipeline using **Azure DevOps** that:

### Pipeline Features

1. **Environment Setup** - Configures Java 17 and Maven
2. **Compilation** - Builds the project with error checking  
3. **Code Style Checks** - Runs Checkstyle validation
4. **Testing** - Executes all unit tests with JaCoCo coverage
5. **Quality Gates** - Enforces coverage thresholds
6. **Artifact Publishing** - Publishes test results and coverage reports
7. **Build Summary** - Provides detailed build information

### Pipeline Configuration

The pipeline is configured in `azure-pipelines.yml` and includes:

- **Trigger**: Runs on `main` branch changes
- **Agent Pool**: Uses your configured agent pool
- **Caching**: Maven dependency caching for faster builds
- **Reporting**: Integrated test results and coverage visualization
- **Quality Gates**: Fails build if quality standards aren't met

### Viewing Results

After each build:
- **Test Results**: Available in Azure DevOps test results tab
- **Coverage Reports**: Integrated into Azure DevOps with line-by-line coverage
- **Checkstyle Reports**: Available in build artifacts
- **Build Artifacts**: JAR files and reports published automatically

## 💡 Learning Objectives

Students will learn:

### Design Patterns
- **Observer Pattern**: Understanding subject-observer relationships
- **Interface Design**: Creating clean, focused interfaces
- **Loose Coupling**: Decoupling subjects from concrete observers

### Java Programming
- **Interface Implementation**: Practical interface usage
- **Collection Frameworks**: LinkedList usage and iteration
- **Exception Handling**: Proper error handling patterns
- **Modern Java Features**: Using Java 17 features effectively

### Software Engineering Practices
- **Unit Testing**: Comprehensive test coverage with JUnit 5
- **Code Quality**: Checkstyle integration and enforcement
- **Documentation**: Javadoc best practices
- **Build Automation**: Maven project structure and lifecycle
- **Continuous Integration**: Automated testing and quality gates

### Professional Development Tools
- **Maven**: Dependency management and build lifecycle
- **JaCoCo**: Code coverage analysis and reporting
- **Checkstyle**: Code style enforcement
- **Azure DevOps**: CI/CD pipeline implementation

## 📁 Project Structure

```
observer-pattern-demo-java/
├── src/
│   ├── main/java/com/observerpattern/
│   │   ├── INodeNavigationListener.java    # Observer interface
│   │   ├── NodeNavigator.java              # Subject implementation
│   │   └── Main.java                       # Demo application
│   └── test/java/com/observerpattern/
│       └── NodeNavigatorTest.java          # Comprehensive tests
├── target/                                 # Build output (generated)
│   ├── classes/                           # Compiled classes
│   ├── test-classes/                      # Compiled test classes
│   ├── site/jacoco/                       # Coverage reports
│   └── site/checkstyle.html              # Style reports
├── pom.xml                                # Maven configuration
├── checkstyle.xml                         # Code style rules
├── azure-pipelines.yml                   # CI/CD configuration
├── .gitignore                            # Git ignore rules
└── README.md                             # This file
```

## 🔍 Example Usage

### Basic Observer Pattern Usage

```java
// Create a navigator with some data
int[] numbers = {1, 2, 3, 4, 5};
NodeNavigator navigator = new NodeNavigator(numbers);

// Create an observer
INodeNavigationListener listener = new INodeNavigationListener() {
    @Override
    public void onNodeVisited(int data) {
        System.out.println("Visited node: " + data);
    }
};

// Subscribe the observer
navigator.subscribe(listener);

// Navigate (observer will be notified for each node)
navigator.navigate();
```

### Custom Observer Implementation

```java
public class StatisticsCollector implements INodeNavigationListener {
    private int sum = 0;
    private int count = 0;
    
    @Override
    public void onNodeVisited(int data) {
        sum += data;
        count++;
    }
    
    public double getAverage() {
        return count > 0 ? (double) sum / count : 0.0;
    }
}

// Usage
StatisticsCollector stats = new StatisticsCollector();
navigator.subscribe(stats);
navigator.navigate();
System.out.println("Average: " + stats.getAverage());
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Make your changes following the coding standards
4. Ensure tests pass and coverage remains high (`mvn test jacoco:report`)
5. Ensure code style compliance (`mvn checkstyle:check`)
6. Commit your changes (`git commit -m 'Add amazing feature'`)
7. Push to the branch (`git push origin feature/amazing-feature`)
8. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [License.txt](License.txt) file for details.

## Note

- Designed for educational purposes to teach object-oriented design patterns
- Includes modern Java development practices and CI/CD pipeline integration

## 📚 Additional Resources

### Design Patterns
- [Gang of Four Design Patterns](https://en.wikipedia.org/wiki/Design_Patterns)
- [Observer Pattern - Refactoring Guru](https://refactoring.guru/design-patterns/observer)
- [Java Design Patterns](https://java-design-patterns.com/patterns/observer/)

### Java Development
- [Oracle Java Documentation](https://docs.oracle.com/en/java/)
- [Maven Getting Started Guide](https://maven.apache.org/guides/getting-started/)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)

### Tools and Practices
- [JaCoCo Documentation](https://www.jacoco.org/jacoco/trunk/doc/)
- [Checkstyle Configuration](https://checkstyle.sourceforge.io/config.html)
- [Azure DevOps Documentation](https://docs.microsoft.com/en-us/azure/devops/)

---

*This project serves as an educational tool for learning design patterns, modern Java development practices, and professional software engineering workflows.*
