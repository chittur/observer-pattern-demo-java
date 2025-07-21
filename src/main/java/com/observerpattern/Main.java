/**
 * ********************************************************************************
 * Filename    = Main.java
 * <p>
 * Author      = Ramaswamy Krishnan-Chittur
 * <p>
 * Product     = Observer Pattern Demo - Java
 * <p>
 * Project     = Observer Pattern Demo
 * <p>
 * Description = Main class demonstrating the Observer design pattern implementation.
 *               Shows how to use NodeNavigator and listeners in a practical example.
 * ********************************************************************************
 */

package com.observerpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Main demonstration class for the Observer Pattern.
 * <p>
 * This class provides executable examples of how the Observer pattern works
 * using the NodeNavigator and INodeNavigationListener implementations.
 * It demonstrates various scenarios including single observers, multiple
 * demonstrations, and error handling.
 * </p>
 * 
 * @author Ramaswamy Krishnan-Chittur
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Main {
    
    /** Standard line separator length for display formatting. */
    private static final int STANDARD_LINE_LENGTH = 80;
    
    /** Shorter line separator length for demo sections. */
    private static final int DEMO_LINE_LENGTH = 40;
    
    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private Main() {
        // Utility class should not be instantiated
    }
    
    /**
     * Main entry point for the Observer Pattern demonstration.
     * <p>
     * Runs several demonstrations showing different aspects of the Observer pattern:
     * <ul>
     *   <li>Basic observer functionality</li>
     *   <li>Multiple observer scenarios</li>
     *   <li>Empty list handling</li>
     *   <li>Unsubscription behavior</li>
     * </ul>
     * </p>
     * 
     * @param args command line arguments (not used)
     */
    public static void main(final String[] args) {
        System.out.println("=".repeat(STANDARD_LINE_LENGTH));
        System.out.println("Observer Pattern Demo - Java Implementation");
        System.out.println("=".repeat(STANDARD_LINE_LENGTH));
        System.out.println();
        
        try {
            // Run various demonstrations
            demonstrateBasicObserver();
            demonstrateMultipleObservers();
            demonstrateEmptyList();
            demonstrateUnsubscription();
            demonstrateErrorHandling();
            
            System.out.println("=".repeat(STANDARD_LINE_LENGTH));
            System.out.println("All demonstrations completed successfully!");
            System.out.println("=".repeat(STANDARD_LINE_LENGTH));
            
        } catch (final Exception e) {
            System.err.println("Error during demonstration: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Demonstrates basic observer functionality.
     * Shows how a single observer receives notifications during navigation.
     */
    private static void demonstrateBasicObserver() {
        System.out.println("Demo 1: Basic Observer Functionality");
        System.out.println("-".repeat(DEMO_LINE_LENGTH));
        
        // Create test data
        final int[] numbers = {10, 20, 30, 40, 50};
        
        // Create navigator and listener
        final NodeNavigator navigator = new NodeNavigator(numbers);
        final SimpleLoggingListener listener = new SimpleLoggingListener("BasicListener");
        
        // Subscribe and navigate
        navigator.subscribe(listener);
        System.out.println("Navigating list: " + java.util.Arrays.toString(numbers));
        navigator.navigate();
        
        // Show results
        System.out.println("Visited nodes: " + listener.getVisitedNodes());
        System.out.println("Total notifications: " + listener.getNotificationCount());
        System.out.println();
    }
    
    /**
     * Demonstrates multiple observer scenarios by switching between different observers.
     */
    private static void demonstrateMultipleObservers() {
        System.out.println("Demo 2: Multiple Observer Scenarios");
        System.out.println("-".repeat(DEMO_LINE_LENGTH));
        
        final int[] numbers = {1, 2, 3, 4, 5};
        final NodeNavigator navigator = new NodeNavigator(numbers);
        
        // First observer - simple logging
        final SimpleLoggingListener logger = new SimpleLoggingListener("Logger");
        navigator.subscribe(logger);
        System.out.println("Navigation with Logger:");
        navigator.navigate();
        System.out.println("Logger recorded: " + logger.getVisitedNodes());
        
        // Second observer - sum calculator
        final SumCalculatorListener calculator = new SumCalculatorListener();
        navigator.subscribe(calculator);
        System.out.println("\nNavigation with Calculator:");
        navigator.navigate();
        System.out.println("Calculator sum: " + calculator.getSum());
        
        // Third observer - statistics collector
        final StatisticsListener stats = new StatisticsListener();
        navigator.subscribe(stats);
        System.out.println("\nNavigation with Statistics:");
        navigator.navigate();
        System.out.println("Statistics: " + stats.getStatistics());
        System.out.println();
    }
    
    /**
     * Demonstrates behavior with empty lists.
     */
    private static void demonstrateEmptyList() {
        System.out.println("Demo 3: Empty List Handling");
        System.out.println("-".repeat(DEMO_LINE_LENGTH));
        
        final int[] emptyArray = {};
        final NodeNavigator navigator = new NodeNavigator(emptyArray);
        final SimpleLoggingListener listener = new SimpleLoggingListener("EmptyListListener");
        
        navigator.subscribe(listener);
        System.out.println("Navigating empty list...");
        navigator.navigate();
        
        System.out.println("Visited nodes: " + listener.getVisitedNodes());
        System.out.println("Total notifications: " + listener.getNotificationCount());
        System.out.println();
    }
    
    /**
     * Demonstrates unsubscription behavior.
     */
    private static void demonstrateUnsubscription() {
        System.out.println("Demo 4: Unsubscription Behavior");
        System.out.println("-".repeat(DEMO_LINE_LENGTH));
        
        final int[] numbers = {100, 200, 300};
        final NodeNavigator navigator = new NodeNavigator(numbers);
        final SimpleLoggingListener listener = new SimpleLoggingListener("UnsubscribeTest");
        
        // Navigate with listener
        navigator.subscribe(listener);
        System.out.println("Navigation with listener:");
        navigator.navigate();
        System.out.println("First run - Visited: " + listener.getVisitedNodes());
        
        // Unsubscribe and navigate again
        navigator.unsubscribe();
        System.out.println("\nNavigation after unsubscribe:");
        navigator.navigate();
        System.out.println("Second run - Visited: " + listener.getVisitedNodes() + " (should be unchanged)");
        System.out.println();
    }
    
    /**
     * Demonstrates error handling scenarios.
     */
    private static void demonstrateErrorHandling() {
        System.out.println("Demo 5: Error Handling");
        System.out.println("-".repeat(DEMO_LINE_LENGTH));
        
        try {
            // Test null array
            System.out.println("Testing null array handling...");
            final NodeNavigator navigator = new NodeNavigator(null);
            System.out.println("ERROR: Should have thrown exception!");
        } catch (final IllegalArgumentException e) {
            System.out.println("✓ Correctly caught exception for null array: " + e.getMessage());
        }
        
        try {
            // Test null listener
            System.out.println("Testing null listener handling...");
            final NodeNavigator navigator = new NodeNavigator(new int[]{1, 2, 3});
            navigator.subscribe(null);
            System.out.println("ERROR: Should have thrown exception!");
        } catch (final IllegalArgumentException | NullPointerException e) {
            System.out.println("✓ Correctly caught exception for null listener: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * Simple logging listener that records visited nodes.
     */
    private static class SimpleLoggingListener implements INodeNavigationListener {
        private final String name;
        private final List<Integer> visitedNodes;
        private int notificationCount;
        
        /**
         * Creates a new logging listener with the specified name.
         * 
         * @param name the name of this listener
         */
        public SimpleLoggingListener(final String name) {
            this.name = name;
            this.visitedNodes = new ArrayList<>();
            this.notificationCount = 0;
        }
        
        @Override
        public void onNodeVisited(final int data) {
            this.visitedNodes.add(data);
            this.notificationCount++;
            System.out.println("  " + this.name + " visited node: " + data);
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
         * Gets the total number of notifications received.
         * 
         * @return notification count
         */
        public int getNotificationCount() {
            return this.notificationCount;
        }
    }
    
    /**
     * Calculator listener that computes the sum of visited nodes.
     */
    private static class SumCalculatorListener implements INodeNavigationListener {
        private int sum;
        
        /**
         * Creates a new sum calculator listener.
         */
        public SumCalculatorListener() {
            this.sum = 0;
        }
        
        @Override
        public void onNodeVisited(final int data) {
            this.sum += data;
            System.out.println("  Calculator: " + data + " (running sum: " + this.sum + ")");
        }
        
        /**
         * Gets the computed sum.
         * 
         * @return the sum of all visited nodes
         */
        public int getSum() {
            return this.sum;
        }
    }
    
    /**
     * Statistics listener that collects statistical information about visited nodes.
     */
    private static class StatisticsListener implements INodeNavigationListener {
        private int count;
        private int sum;
        private int min = Integer.MAX_VALUE;
        private int max = Integer.MIN_VALUE;
        
        /**
         * Creates a new statistics listener.
         */
        public StatisticsListener() {
            this.count = 0;
            this.sum = 0;
        }
        
        @Override
        public void onNodeVisited(final int data) {
            this.count++;
            this.sum += data;
            this.min = Math.min(this.min, data);
            this.max = Math.max(this.max, data);
            System.out.println("  Statistics: processed " + data + " (count: " + this.count + ")");
        }
        
        /**
         * Gets the computed statistics.
         * 
         * @return a string representation of the statistics
         */
        public String getStatistics() {
            if (this.count == 0) {
                return "No data";
            }
            final double average = (double) this.sum / this.count;
            return String.format("Count=%d, Sum=%d, Min=%d, Max=%d, Avg=%.2f", 
                               this.count, this.sum, this.min, this.max, average);
        }
    }
}
