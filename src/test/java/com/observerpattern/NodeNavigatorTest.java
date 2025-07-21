/**
 * Comprehensive unit tests for the Observer pattern demonstration.
 * <p>
 * This test class validates the behavior of the NodeNavigator and its interaction
 * with observers. It covers normal operation, edge cases, error conditions, and
 * the proper implementation of the Observer design pattern using modern JUnit 5
 * testing practices including nested test classes and parameterized tests.
 * </p>
 * 
 * @author Ramaswamy Krishnan-Chittur
 * @version 1.0.0
 * @since 1.0.0
 */

package com.observerpattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Comprehensive unit tests for the Observer pattern demo.
 * <p>
 * This test class validates the behavior of the NodeNavigator and its interaction
 * with observers. It covers normal operation, edge cases, error conditions, and
 * the proper implementation of the Observer design pattern.
 * </p>
 * 
 * @author Ramaswamy Krishnan-Chittur
 * @version 1.0.0
 * @since 1.0.0
 */
@DisplayName("NodeNavigator Tests")
class NodeNavigatorTest {
    
    /** Test value for single element navigation tests. */
    private static final int SINGLE_TEST_VALUE = 42;
    
    /** Expected notification count for multiple navigation test. */
    private static final int EXPECTED_MULTIPLE_NAVIGATION_COUNT = 6;
    
    /** Small array size for performance tests. */
    private static final int SMALL_ARRAY_SIZE = 100;
    
    /** Medium array size for performance tests. */
    private static final int MEDIUM_ARRAY_SIZE = 1000;
    
    /** Large array size for performance tests. */
    private static final int LARGE_ARRAY_SIZE = 10000;
    
    /** Maximum allowed time for performance tests in milliseconds. */
    private static final long MAX_PERFORMANCE_TIME_MS = 1000;
    
    /** Test listener instance used across test methods. */
    private TestNodeListener testListener;
    
    /**
     * Sets up test fixtures before each test method.
     * 
     * @param testInfo information about the current test
     */
    @BeforeEach
    void setUp(final TestInfo testInfo) {
        testListener = new TestNodeListener();
        System.out.println("Running test: " + testInfo.getDisplayName());
    }
    
    /**
     * Nested class for testing basic functionality.
     */
    @Nested
    @DisplayName("Basic Functionality Tests")
    class BasicFunctionalityTests {
        
        /**
         * Tests navigation callbacks with a populated list.
         * This is the core test converted from the original C# implementation.
         */
        @Test
        @DisplayName("Should notify observer for each node during navigation")
        void testNavigationCallbacks() {
            // Arrange
            final int[] numbers = {1, 2, 3, 4, 5};
            final NodeNavigator navigator = new NodeNavigator(numbers);
            
            // Act
            navigator.subscribe(testListener);
            navigator.navigate();
            
            // Assert
            assertArrayEquals(numbers, testListener.getVisitedNodesArray(),
                            "Observer should receive notifications for all nodes in order");
            assertEquals(numbers.length, testListener.getNotificationCount(),
                       "Observer should receive exactly one notification per node");
        }
        
        /**
         * Tests that no navigation callbacks occur when the list is empty.
         * This is converted from the original C# implementation.
         */
        @Test
        @DisplayName("Should not notify observer when navigating empty list")
        void testEmptyNavigationHasNoCallback() {
            // Arrange
            final int[] emptyNumbers = {};
            final NodeNavigator navigator = new NodeNavigator(emptyNumbers);
            
            // Act
            navigator.subscribe(testListener);
            navigator.navigate();
            
            // Assert
            assertTrue(testListener.getVisitedNodes().isEmpty(),
                     "Observer should not receive any notifications for empty list");
            assertEquals(0, testListener.getNotificationCount(),
                       "Notification count should be zero for empty list");
        }
        
        /**
         * Tests navigation with a single element.
         */
        @Test
        @DisplayName("Should handle single element navigation correctly")
        void testSingleElementNavigation() {
            // Arrange
            final int[] singleNumber = {SINGLE_TEST_VALUE};
            final NodeNavigator navigator = new NodeNavigator(singleNumber);
            
            // Act
            navigator.subscribe(testListener);
            navigator.navigate();
            
            // Assert
            assertEquals(1, testListener.getVisitedNodes().size(),
                       "Should visit exactly one node");
            assertEquals(SINGLE_TEST_VALUE, testListener.getVisitedNodes().get(0),
                       "Should visit the correct node value");
        }
    }
    
    /**
     * Nested class for testing observer management.
     */
    @Nested
    @DisplayName("Observer Management Tests")
    class ObserverManagementTests {
        
        /**
         * Tests subscription and unsubscription behavior.
         */
        @Test
        @DisplayName("Should handle subscription and unsubscription correctly")
        void testSubscriptionAndUnsubscription() {
            // Arrange
            final int[] numbers = {10, 20, 30};
            final NodeNavigator navigator = new NodeNavigator(numbers);
            
            // Test initial state
            assertNull(navigator.getListener(), "Initially should have no listener");
            
            // Test subscription
            navigator.subscribe(testListener);
            assertSame(testListener, navigator.getListener(), "Should store the subscribed listener");
            
            // Test unsubscription
            navigator.unsubscribe();
            assertNull(navigator.getListener(), "Should have no listener after unsubscribe");
        }
        
        /**
         * Tests that navigation works without a subscribed observer.
         */
        @Test
        @DisplayName("Should navigate successfully without observer")
        void testNavigationWithoutObserver() {
            // Arrange
            final int[] numbers = {1, 2, 3};
            final NodeNavigator navigator = new NodeNavigator(numbers);
            
            // Act & Assert (should not throw exception)
            assertDoesNotThrow(() -> navigator.navigate(),
                             "Navigation should work without an observer");
        }
        
        /**
         * Tests that unsubscribing prevents further notifications.
         */
        @Test
        @DisplayName("Should not notify observer after unsubscription")
        void testNoNotificationAfterUnsubscribe() {
            // Arrange
            final int[] numbers = {100, 200, 300};
            final NodeNavigator navigator = new NodeNavigator(numbers);
            
            // First navigation with observer
            navigator.subscribe(testListener);
            navigator.navigate();
            final int firstRunCount = testListener.getNotificationCount();
            
            // Unsubscribe and navigate again
            navigator.unsubscribe();
            navigator.navigate();
            final int secondRunCount = testListener.getNotificationCount();
            
            // Assert
            assertEquals(firstRunCount, secondRunCount,
                       "Notification count should not increase after unsubscribe");
        }
        
        /**
         * Tests that subscribing a new observer replaces the previous one.
         */
        @Test
        @DisplayName("Should replace observer when subscribing a new one")
        void testObserverReplacement() {
            // Arrange
            final int[] numbers = {1, 2};
            final NodeNavigator navigator = new NodeNavigator(numbers);
            final TestNodeListener secondListener = new TestNodeListener();
            
            // Subscribe first observer
            navigator.subscribe(testListener);
            navigator.navigate();
            
            // Subscribe second observer (should replace first)
            navigator.subscribe(secondListener);
            navigator.navigate();
            
            // Assert
            assertEquals(2, testListener.getNotificationCount(),
                       "First observer should only receive notifications from first navigation");
            assertEquals(2, secondListener.getNotificationCount(),
                       "Second observer should receive notifications from second navigation");
            assertSame(secondListener, navigator.getListener(),
                     "Navigator should reference the second observer");
        }
    }
    
    /**
     * Nested class for testing error conditions and edge cases.
     */
    @Nested
    @DisplayName("Error Handling and Edge Cases")
    class ErrorHandlingTests {
        
        /**
         * Tests constructor with null array.
         */
        @Test
        @DisplayName("Should throw exception for null array in constructor")
        void testConstructorWithNullArray() {
            // Act & Assert
            final IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new NodeNavigator(null),
                "Constructor should throw exception for null array"
            );
            assertEquals("Numbers array cannot be null", exception.getMessage(),
                       "Exception should have appropriate message");
        }
        
        /**
         * Tests subscription with null listener.
         */
        @Test
        @DisplayName("Should throw exception for null listener subscription")
        void testSubscribeWithNullListener() {
            // Arrange
            final NodeNavigator navigator = new NodeNavigator(new int[]{1, 2, 3});
            
            // Act & Assert
            assertThrows(NullPointerException.class,
                       () -> navigator.subscribe(null),
                       "Subscribe should throw exception for null listener");
        }
        
        /**
         * Tests navigation with large arrays.
         * 
         * @param size the size of the array to test
         */
        @ParameterizedTest
        @ValueSource(ints = {SMALL_ARRAY_SIZE, MEDIUM_ARRAY_SIZE, LARGE_ARRAY_SIZE})
        @DisplayName("Should handle large arrays efficiently")
        void testLargeArrayNavigation(final int size) {
            // Arrange
            final int[] largeArray = new int[size];
            for (int i = 0; i < size; i++) {
                largeArray[i] = i + 1;
            }
            final NodeNavigator navigator = new NodeNavigator(largeArray);
            
            // Act
            navigator.subscribe(testListener);
            final long startTime = System.currentTimeMillis();
            navigator.navigate();
            final long endTime = System.currentTimeMillis();
            
            // Assert
            assertEquals(size, testListener.getNotificationCount(),
                       "Should notify for all elements in large array");
            assertTrue((endTime - startTime) < MAX_PERFORMANCE_TIME_MS,
                     "Navigation should complete within reasonable time");
        }
        
        /**
         * Tests navigation with arrays containing negative numbers.
         */
        @Test
        @DisplayName("Should handle negative numbers correctly")
        void testNegativeNumbers() {
            // Arrange
            final int[] negativeNumbers = {-1, -2, -3, 0, 1, 2, 3};
            final NodeNavigator navigator = new NodeNavigator(negativeNumbers);
            
            // Act
            navigator.subscribe(testListener);
            navigator.navigate();
            
            // Assert
            assertArrayEquals(negativeNumbers, testListener.getVisitedNodesArray(),
                            "Should handle negative numbers correctly");
        }
    }
    
    /**
     * Nested class for testing navigation state and behavior.
     */
    @Nested
    @DisplayName("Navigation State Tests")
    class NavigationStateTests {
        
        /**
         * Tests the size and isEmpty methods.
         */
        @Test
        @DisplayName("Should report correct size and empty status")
        void testSizeAndEmptyStatus() {
            // Test empty navigator
            final NodeNavigator emptyNavigator = new NodeNavigator(new int[]{});
            assertTrue(emptyNavigator.isEmpty(), "Empty navigator should report isEmpty as true");
            assertEquals(0, emptyNavigator.size(), "Empty navigator should report size as 0");
            
            // Test populated navigator
            final int[] numbers = {1, 2, 3, 4, 5};
            final NodeNavigator navigator = new NodeNavigator(numbers);
            assertFalse(navigator.isEmpty(), "Populated navigator should report isEmpty as false");
            assertEquals(numbers.length, navigator.size(), "Navigator should report correct size");
        }
        
        /**
         * Tests toString method.
         */
        @Test
        @DisplayName("Should provide meaningful string representation")
        void testToStringMethod() {
            // Test empty navigator
            final NodeNavigator emptyNavigator = new NodeNavigator(new int[]{});
            final String emptyString = emptyNavigator.toString();
            assertTrue(emptyString.contains("size=0"), "ToString should include size");
            assertTrue(emptyString.contains("hasListener=false"), "ToString should include listener status");
            
            // Test populated navigator with listener
            final int[] numbers = {1, 2, 3};
            final NodeNavigator navigator = new NodeNavigator(numbers);
            navigator.subscribe(testListener);
            final String populatedString = navigator.toString();
            assertTrue(populatedString.contains("size=3"), "ToString should include correct size");
            assertTrue(populatedString.contains("hasListener=true"), "ToString should include listener status");
        }
        
        /**
         * Tests multiple navigation calls.
         */
        @Test
        @DisplayName("Should handle multiple navigation calls correctly")
        void testMultipleNavigationCalls() {
            // Arrange
            final int[] numbers = {10, 20};
            final NodeNavigator navigator = new NodeNavigator(numbers);
            navigator.subscribe(testListener);
            
            // Act - navigate multiple times
            navigator.navigate();
            navigator.navigate();
            navigator.navigate();
            
            // Assert
            assertEquals(EXPECTED_MULTIPLE_NAVIGATION_COUNT, testListener.getNotificationCount(),
                       "Should receive notifications for each navigation call");
            
            // Verify the pattern of notifications
            final List<Integer> visited = testListener.getVisitedNodes();
            final List<Integer> expected = Arrays.asList(10, 20, 10, 20, 10, 20);
            assertEquals(expected, visited,
                       "Should repeat the same pattern for each navigation");
        }
    }
    
    /**
     * Test helper class that implements INodeNavigationListener for testing purposes.
     */
    private static class TestNodeListener implements INodeNavigationListener {
        /** List of nodes that have been visited. */
        private final List<Integer> visitedNodes;
        
        /** Count of notifications received. */
        private int notificationCount;
        
        /**
         * Creates a new test listener.
         */
        TestNodeListener() {
            this.visitedNodes = new ArrayList<>();
            this.notificationCount = 0;
        }
        
        @Override
        public void onNodeVisited(final int data) {
            this.visitedNodes.add(data);
            this.notificationCount++;
        }
        
        /**
         * Gets the list of visited nodes.
         * 
         * @return list of visited node values
         */
        public List<Integer> getVisitedNodes() {
            return new ArrayList<>(this.visitedNodes);
        }
        
        /**
         * Gets the visited nodes as an array.
         * 
         * @return array of visited node values
         */
        public int[] getVisitedNodesArray() {
            return this.visitedNodes.stream().mapToInt(Integer::intValue).toArray();
        }
        
        /**
         * Gets the total number of notifications received.
         * 
         * @return notification count
         */
        public int getNotificationCount() {
            return this.notificationCount;
        }
        
        /**
         * Resets the listener state.
         */
        public void reset() {
            this.visitedNodes.clear();
            this.notificationCount = 0;
        }
    }
}
