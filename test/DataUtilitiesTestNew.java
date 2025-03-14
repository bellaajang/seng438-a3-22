package org.jfree.data;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.jfree.data.Values2D;



public class DataUtilitiesTestNew extends DataUtilities {

	private Mockery mockingContext;
	private Mockery mockingContext1;
	private Mockery mockingContext2;
	private Mockery mockingContext3;
	private Mockery mockingContext4;

    private Values2D values6;
	private Values2D values;
	private Values2D values1;
	private KeyedValues keyed;
	private KeyedValues keyed2;
	private KeyedValues keyed3;
	private KeyedValues keyedAllNulls;
    private Mockery mockingContext6;
    private Values2D mockValues;
    

    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { 
    	mockingContext6 = new Mockery();
    	mockValues = mockingContext6.mock(Values2D.class);
    	mockingContext = new Mockery();
    	
    	
    	values = mockingContext.mock(Values2D.class);
    	mockingContext.checking(new Expectations() {
        {
            one(values).getColumnCount();
            will(returnValue(2));
            one(values).getRowCount();
            will(returnValue(3));
            one(values).getValue(0, 0);
            will(returnValue(5.5));
            one(values).getValue(0, 1);
            will(returnValue(2.5));
            one(values).getValue(-1, 0);
            will(returnValue(null));
            one(values).getValue(-1, 1);
            will(returnValue(null));
            one(values).getValue(3, 0);
            will(returnValue(null));
            one(values).getValue(3, 1);
            will(returnValue(null));
            one(values).getValue(1, 0);
            will(returnValue(-5.5));
            one(values).getValue(1, 1);
            will(returnValue(-2.5));
            one(values).getValue(2, 0);
            will(returnValue(0));
            one(values).getValue(2, 1);
            will(returnValue(0));
        }
    });
    	
        
    	mockingContext1 = new Mockery();
    	
    	values1 = mockingContext1.mock(Values2D.class);
    	mockingContext1.checking(new Expectations() {
        {
            one(values1).getRowCount();
            will(returnValue(2));
            one(values1).getColumnCount();
            will(returnValue(3));
            
            one(values1).getValue(0, 0);
            will(returnValue(3));
            one(values1).getValue(0, 1);
            will(returnValue(0));
            one(values1).getValue(0, -1);
            will(returnValue(null));
            one(values1).getValue(1, -1);
            will(returnValue(null));
            one(values1).getValue(0,3);
            will(returnValue(null));
            one(values1).getValue(1,3);
            will(returnValue(null));
            one(values1).getValue(0, 2);
            will(returnValue(-5.5));
            one(values1).getValue(1, 0);
            will(returnValue(5));
            one(values1).getValue(1, 1);
            will(returnValue(0));
            one(values1).getValue(1, 2);
            will(returnValue(-1));
        }
    }); 
    	
    	mockingContext2 = new Mockery();
    	
    	keyed = mockingContext2.mock(KeyedValues.class);
    	mockingContext2.checking(new Expectations() {
        {
        	allowing(keyed).getItemCount();
            will(returnValue(3));
            allowing(keyed).getKey(0);
            will(returnValue("0"));
            allowing(keyed).getKey(1);
            will(returnValue("1"));
            allowing(keyed).getKey(2);
            will(returnValue("2"));
            allowing(keyed).getValue(0);
            will(returnValue(5));
            allowing(keyed).getValue(1);
            will(returnValue(10));
            allowing(keyed).getValue(2);
            will(returnValue(5));
      
        	
        }
    });
    	
    	mockingContext3 = new Mockery();
    	
    	keyed2 = mockingContext3.mock(KeyedValues.class);
    	mockingContext3.checking(new Expectations() {
        {
        	allowing(keyed2).getItemCount();
            will(returnValue(3));
            allowing(keyed2).getKey(0);
            will(returnValue("0"));
            allowing(keyed2).getKey(1);
            will(returnValue("1"));
            allowing(keyed2).getKey(2);
            will(returnValue("2"));
            allowing(keyed2).getValue(0);
            will(returnValue(-5));
            allowing(keyed2).getValue(1);
            will(returnValue(10));
            allowing(keyed2).getValue(2);
            will(returnValue(5));
      
        	
        }
    });
    	
    	mockingContext4 = new Mockery();
    	
    	keyed3 = mockingContext4.mock(KeyedValues.class);
    	mockingContext4.checking(new Expectations() {
        {
        	allowing(keyed3).getItemCount();
            will(returnValue(3));
            allowing(keyed3).getKey(0);
            will(returnValue("0"));
            allowing(keyed3).getKey(1);
            will(returnValue("1"));
            allowing(keyed3).getKey(2);
            will(returnValue("2"));
            allowing(keyed3).getValue(0);
            will(returnValue(-5));
            allowing(keyed3).getValue(1);
            will(returnValue(-10));
            allowing(keyed3).getValue(2);
            will(returnValue(-5));
      
        	
        }
    });
    	
    	Mockery mockingContext5 = new Mockery();
        keyedAllNulls = mockingContext5.mock(KeyedValues.class);

        mockingContext5.checking(new Expectations() {{
            allowing(keyedAllNulls).getItemCount();
            will(returnValue(3));
            allowing(keyedAllNulls).getKey(0);
            will(returnValue("0"));
            allowing(keyedAllNulls).getKey(1);
            will(returnValue("1"));
            allowing(keyedAllNulls).getKey(2);
            will(returnValue("2"));
            allowing(keyedAllNulls).getValue(0);
            will(returnValue(5));
            allowing(keyedAllNulls).getValue(1);
            will(returnValue(null));
            allowing(keyedAllNulls).getValue(2);
            will(returnValue(15));
        }});

        
    }
    
    // Testing getCumulativePercentages method
    @Test
    public void testGetCumulativePercentagesWithNullData() {
    	try {
            DataUtilities.getCumulativePercentages(null);
            fail("Expected an Exception to be thrown.");
        } catch (Exception e) {
            // Test passes, exception was thrown as expected
        }
    }
    
    @Test
    public void testGetCumulativePercentagesForANullValue() {
   	KeyedValues result = DataUtilities.getCumulativePercentages(keyedAllNulls);

	double[] percents = {0.25, 0.25, 1.0};

	for (int i = 0; i < percents.length ; i++) {
        assertEquals(percents[i], result.getValue(i).doubleValue(), 0.00000001d);
    }
   }
    
    @Test
	 public void testGetCumulativePercentagesForAllPositives() {
    	double[] percents = {0.25, 0.75, 1.0};
	        
	        KeyedValues result = DataUtilities.getCumulativePercentages(keyed);
	
	        for (int i = 0; i < percents.length ; i++) {
	            assertEquals(percents[i], result.getValue(i).doubleValue(), 0.00000001d);
	        }

	 }
    
    @Test
	 public void testGetCumulativePercentagesForAllNegatives() {
   	double[] percents = {0.25, 0.75, 1.0};
	        
	        KeyedValues result = DataUtilities.getCumulativePercentages(keyed3);
	
	        for (int i = 0; i < percents.length ; i++) {
	            assertEquals(percents[i], result.getValue(i).doubleValue(), 0.00000001d);
	        }

	 }
    
    @Test
	 public void testGetCumulativePercentagesForTwoPositivesOneNegative() {
   	double[] percents = {-0.5, 0.5, 1.0};
	        
	        KeyedValues result = DataUtilities.getCumulativePercentages(keyed2);
	
	        for (int i = 0; i < percents.length ; i++) {
	            assertEquals(percents[i], result.getValue(i).doubleValue(), 0.00000001d);
	        }

	 }
    
    // Testing CalculateRowTotal method
    
    @Test
	 public void testCalculateRowTotalWithNullData() {
    	try {
            DataUtilities.calculateRowTotal(null, 0);
            fail("Expected an Exception to be thrown.");
        } catch (Exception e) {
            // Test passes, exception was thrown as expected
        }
    }
    
    @Test
	 public void testCalculateRowTotalWithNegativeIndex() {
   	try {
           DataUtilities.calculateRowTotal(values, -1);
           fail("Expected an Exception to be thrown.");
       } catch (Exception e) {
           // Test passes, exception was thrown as expected
       }
   }
    
    @Test
	 public void testCalculateRowTotalWithOutOfScopeIndex() {
   	try {
           DataUtilities.calculateRowTotal(values, 3);
           fail("Expected an Exception to be thrown.");
       } catch (Exception e) {
           // Test passes, exception was thrown as expected
       }
   }

	
	@Test
	 public void testCalculateRowTotalForTwoPositiveValues() {
	     // setup
	     double result = DataUtilities.calculateRowTotal(values, 0);
	     // verify
	     assertEquals(8.0, result, .000000001d);
	 }
	
	@Test
	 public void testCalculateRowTotalForTwoNegativeValues() {
	     // setup
	     double result = DataUtilities.calculateRowTotal(values, 1);
	     // verify
	     assertEquals(-8.0, result, .000000001d);
	 }
	
	@Test
	 public void testCalculateRowTotalForTwoZeroValues() {
	     // setup
	     double result = DataUtilities.calculateRowTotal(values, 2);
	     // verify
	     assertEquals(0.0, result, .000000001d);
	 }
	
	//Test cases for calculateColumnTotal(Values2D data, int column) method
	
	// Test for null data
	    @Test
	    public void testCalculateColumnTotalWithNullData() {
	        try {
	            DataUtilities.calculateColumnTotal(null, 0);
	            fail("Expected an Exception to be thrown.");
	        } catch (Exception e) {
	            // Test passes, exception was thrown as expected
	        }
	    }

	    // Test for negative thus invalid index
	    @Test
	    public void testCalculateColumnTotalWithNegativeIndex() {
	        try {
	            DataUtilities.calculateColumnTotal(values1, -1);
	            fail("Expected an Exception to be thrown.");
	        } catch (Exception e) {
	            // Test passes, exception was thrown as expected
	        }
	    }

	    // Test for out-of-scope thus invalid index
	    @Test
	    public void testCalculateColumnTotalWithOutOfScopeIndex() {
	        try {
	            DataUtilities.calculateColumnTotal(values1, 3);
	            fail("Expected an Exception to be thrown.");
	        } catch (Exception e) {
	            // Test passes, exception was thrown as expected
	        }
	    }

	    // Test for two positive values
	    @Test
	    public void testCalculateColumnTotalForTwoPositiveValues() {
	        double result = DataUtilities.calculateColumnTotal(values1, 0);
	        assertEquals(8.0, result, .000000001d);
	    }

	    // Test for two negative values
	    @Test
	    public void testCalculateColumnTotalForTwoNegativeValues() {
	        double result = DataUtilities.calculateColumnTotal(values1, 1);
	        assertEquals(-6.5, result, .000000001d);
	    }

	    // Test for two zero values
	    @Test
	    public void testCalculateColumnTotalForTwoZeroValues() {
	        double result = DataUtilities.calculateColumnTotal(values1, 1);
	        assertEquals(0.0, result, .000000001d);
	    }

	//Test cases for createNumberArray2D(double[][] data) method
    @Test
    //test for creating an invaild 2Darray of nulls
	public void testCreateNumber2DArrayofNulls() {
		try {
			double[][] array = null;
			
			DataUtilities.createNumberArray2D(array);
			
			fail("Expected is throwing an exception.");
			
		} catch (Exception e) {
			assertEquals("The exception thrown is", IllegalArgumentException.class,
					e.getClass());
		}
	}
    
    @Test
    //test for 2Darrays of positives
	public void testCompareCreateNumber2DArrayofPostivies() {
    	
    	Number[][] expected = { {1.0,2.0}, {3.0,4.0} };
		double[][] array = { {1.0,2.0}, {3.0,4.0} };
		
		assertArrayEquals("createNumber2DArray() failed. The exception thrown is", expected, DataUtilities.createNumberArray2D(array));
    }
    
	public void testCompareCreateNumber2DArrayofPosandNeg() {
    	
    	Number[][] expected = { {-1.0,2.0}, {-3.0,4.0} };
		double[][] array = { {-1.0,2.0}, {-3.0,4.0} };
		
		assertArrayEquals("createNumber2DArray() failed. The exception thrown is", expected, DataUtilities.createNumberArray2D(array));
    }
    
    @Test
    //test for 2Darrays of zeros
	public void testCompareCreateNumber2DArrayofZeros() {
    	
		Number[][] expected = { {0,0}, {0,0} };
		double[][] array = { {0,0}, {0,0} };
		
		assertArrayEquals("createNumber2DArray() failed. The exception thrown is", expected, DataUtilities.createNumberArray2D(array));
    }
    
    @Test
    //test for empty 2Darrays
	public void testCompareCreateNumber2DArrayofEmpty() {

		Number[][] expected = { {}, {} };
		double[][] array = { {}, {} };
		
		assertArrayEquals("createNumber2DArray() failed. The exception thrown is", expected, DataUtilities.createNumberArray2D(array));
    }
    
    @Test
    //test for 2Darrays of negatives
	public void testCompareCreateNumber2DArrayofNegatives() {

		Number[][] expected = { {-1.0,-2.0}, {-3.0,-4.0} };
		double[][] array = { {-1.0,-2.0}, {-3.0,-4.0} };
		
		assertArrayEquals("createNumber2DArray() failed. The exception thrown is", expected, DataUtilities.createNumberArray2D(array));
    }
    
    @Test
    //test for 2Darrays of only rows
	public void testCompareCreateNumber2DArrayofOnlyRows() {
        double[][] array = {
                {10.5, 20.5, 30.5}
        };
        Number[][] expected = {
                {10.5, 20.5, 30.5}
        };
        assertArrayEquals("createNumber2DArray() failed. The exception thrown is", expected, DataUtilities.createNumberArray2D(array));
    }
    
    @Test
    //test for 2Darrays of only cols
	public void testCompareCreateNumber2DArrayofOnlyCols() {
        double[][] array = {
                {1.1},
                {2.2},
                {3.3}
            };
        Number[][] expected = {
                {1.1},
                {2.2},
                {3.3}
        };
        assertArrayEquals("createNumber2DArray() failed. The exception thrown is", expected, DataUtilities.createNumberArray2D(array));
    }
    
    @Test
    //test for non-rectangle 2Darrays
	public void testCompareCreateNumber2DArraynonRectangle() {
        double[][] array = {
                {1.1, 2.2},
                {3.3, 4.4, 5.5},
                {6.6}
            };
        Number[][] expected = {
            {1.1, 2.2},
            {3.3, 4.4, 5.5},
            {6.6}
        };
        assertArrayEquals("createNumber2DArray() failed. The exception thrown is", expected, DataUtilities.createNumberArray2D(array));
    }
    
    @Test
    public void testCreateNumber2DArrayofLargeValues() {
        double[][] array = {
            {Double.MAX_VALUE, Double.MIN_VALUE},
            {-Double.MAX_VALUE, -Double.MIN_VALUE}
        };
        Number[][] expected = {
            {Double.MAX_VALUE, Double.MIN_VALUE},
            {-Double.MAX_VALUE, -Double.MIN_VALUE}
        };

        assertArrayEquals("createNumber2DArray() failed. The exception thrown is", expected, DataUtilities.createNumberArray2D(array));
    }

    //Test Cases for CreateNumberArray method

    //test for creating a number array with null data
    @Test
    public void testCreateNumberArrayWithNullData() {
        try {
            DataUtilities.createNumberArray(null);
            fail("Expected an Exception to be thrown.");
        } catch (Exception e) {
            // Test passes if exception is thrown
        }
    }

    //test for creating an empty number array
    @Test
    public void testCreateNumberArrayWithEmptyArray() {
        try {
            Number[] result = DataUtilities.createNumberArray(new double[0]);
            assertNotNull(result);
            assertEquals(0, result.length);
        } catch (Exception e) {
            fail("Exception thrown unexpectedly: " + e.getMessage());
        }
    }

    //test for creating a number array with all positive numbers
    @Test
    public void testCreateNumberArrayWithAllPositiveValues() {
        try {
            double[] input = {1.0, 2.5, 3.3};
            Number[] result = DataUtilities.createNumberArray(input);

            assertNotNull("Result array is null", result);
            assertEquals(3, result.length);
            assertNotNull("Result[2] is null", result[2]);

            assertEquals(1.0, result[0].doubleValue(), 0.000000001d);
            assertEquals(2.5, result[1].doubleValue(), 0.000000001d);
            assertEquals(3.3, result[2].doubleValue(), 0.000000001d);
        } catch (Exception e) {
            fail("Exception thrown unexpectedly: " + e.getMessage());
        }
    }

    //test for creating a number array with all negative numbers
    @Test
    public void testCreateNumberArrayWithAllNegativeValues() {
        try {
            double[] input = {-1.0, -2.5, -3.3};
            Number[] result = DataUtilities.createNumberArray(input);

            assertNotNull("Result array is null", result);
            assertEquals(3, result.length);
            assertNotNull("Result[2] is null", result[2]);

            assertEquals(-1.0, result[0].doubleValue(), 0.000000001d);
            assertEquals(-2.5, result[1].doubleValue(), 0.000000001d);
            assertEquals(-3.3, result[2].doubleValue(), 0.000000001d);
        } catch (Exception e) {
            fail("Exception thrown unexpectedly: " + e.getMessage());
        }
    }

    //test for creating a number array with positive and negative values
    @Test
    public void testCreateNumberArrayWithMixedValues() {
        try {
            double[] input = {-1.5, 0.0, 2.8};
            Number[] result = DataUtilities.createNumberArray(input);

            assertNotNull("Result array is null", result);
            assertEquals(3, result.length);
            assertNotNull("Result[2] is null", result[2]);

            assertEquals(-1.5, result[0].doubleValue(), 0.000000001d);
            assertEquals(0.0, result[1].doubleValue(), 0.000000001d);
            assertEquals(2.8, result[2].doubleValue(), 0.000000001d);
        } catch (Exception e) {
            fail("Exception thrown unexpectedly: " + e.getMessage());
        }
    }

    //test for creating a number array with all zero values
    @Test
    public void testCreateNumberArrayWithZeroValues() {
        try {
            double[] input = {0.0, 0.0, 0.0};
            Number[] result = DataUtilities.createNumberArray(input);

            assertNotNull("Result array is null", result);
            assertEquals(3, result.length);
            assertNotNull("Result[2] is null", result[2]);

            assertEquals(0.0, result[0].doubleValue(), 0.000000001d);
            assertEquals(0.0, result[1].doubleValue(), 0.000000001d);
            assertEquals(0.0, result[2].doubleValue(), 0.000000001d);
        } catch (Exception e) {
            fail("Exception thrown unexpectedly: " + e.getMessage());
        }
    }
    
//Test cases for Equal
    
    @Test
    public void testBothArraysNull() {
        assertTrue(equal(null, null));
    }

    @Test
    public void testOneArrayNulla() {
        double[][] a = null;
        double[][] b = {{1.0}};
        assertFalse(equal(a, b));
    }
    
    @Test
    public void testOneArrayNullb() {
        double[][] a = {{1.0}};
        double[][] b = null;
        assertFalse(equal(a, b));
    }

    @Test
    public void testDifferentArrayLengths() {
        double[][] a = {{1.0}};
        double[][] b = {{1.0}, {2.0}};
        assertFalse(equal(a, b));
    }

    @Test
    public void testNestedArrayInequality() {
        double[][] a = {{1.0, 2.0}, {3.0, 4.0}};
        double[][] b = {{1.0, 2.0}, {4.0, 3.0}};
        assertFalse(equal(a, b));
    }

    @Test
    public void testNestedArrayEquality() {
        double[][] a = {{1.0, 2.0}, {3.0, 4.0}};
        double[][] b = {{1.0, 2.0}, {3.0, 4.0}};
        assertTrue(equal(a, b));
    }
    
    //clone method
    @Test
    public void testClone_NullSource() {
        try {
            double[][] result = clone(null);
            fail("Expected IllegalArgumentException for null input.");
        } catch (IllegalArgumentException e) {
            assertEquals("source", e.getMessage());
        }
    }
    
    @Test
    public void testClone_EmptyArray() {
        double[][] source = new double[0][];
        double[][] result = clone(source);

        assertNotNull(result);
        assertEquals(0, result.length);
    }

    @Test
    public void testClone_NonNullRows() {
        double[][] source = { {1.1, 2.2}, {3.3, 4.4} };
        double[][] result = clone(source);

        assertNotSame(source, result);
        assertArrayEquals(source[0], result[0], 0.000001);
        assertArrayEquals(source[1], result[1], 0.000001);
    }

    @Test
    public void testClone_WithNullRows() {
        double[][] source = { {1.1, 2.2}, null, {3.3, 4.4} };
        double[][] result = clone(source);

        assertNotSame(source, result);
        assertArrayEquals(source[0], result[0], 0.000001);
        assertNull(result[1]); 
        assertArrayEquals(source[2], result[2], 0.000001);
    }

    @Test
    public void testClone_JaggedArray() {
        double[][] source = { {1.1}, {2.2, 3.3}, {4.4, 5.5, 6.6} };
        double[][] result = clone(source);

        assertNotSame(source, result);
        assertArrayEquals(source[0], result[0], 0.000001);
        assertArrayEquals(source[1], result[1], 0.000001);
        assertArrayEquals(source[2], result[2], 0.000001);
    }
    
  //Test cases for calculateRowTotal(Values2D, int, int[])
    
    @Test
    public void testCalculateRowTotalWithNegativeColumn() {
        mockingContext6.checking(new Expectations() {{
            oneOf(mockValues).getColumnCount();
            will(returnValue(-1)); // Assume no rows in the data

            oneOf(mockValues).getValue(0, -1);
            will(returnValue(null)); // Mocking a null value for the specific row and column
        }});

        int[] validRows = {0}; // Test only the first row
        double result = DataUtilities.calculateRowTotal(mockValues, 1, validRows);

        // Verifying that null value is handled properly, result should remain 0.0
        assertEquals(0.0, result, 0.0000001);
    }

    @Test
    public void testCalculateColumnTotalWithNullDataAndValidRows() {
        try {
            DataUtilities.calculateColumnTotal(null, 0, new int[]{0, 1});
            fail("Expected an Exception to be thrown.");
        } catch (IllegalArgumentException e) {
            assertEquals("data", e.getMessage());
        }
    }

    @Test
    public void testCalculateColumnTotalWithEmptyValidRows() {
        mockingContext.checking(new Expectations() {{
            one(values1).getRowCount();
            will(returnValue(3));
        }});

        double result = DataUtilities.calculateColumnTotal(values1, 1, new int[]{});
        assertEquals(0.0, result, 0.000001);
    }

    @Test
    public void testCalculateColumnTotalWithValidRows() {
        mockingContext.checking(new Expectations() {{
            one(values1).getRowCount();
            will(returnValue(3));
            one(values1).getValue(0, 1);
            will(returnValue(5.0));
            one(values1).getValue(1, 1);
            will(returnValue(10.0));
        }});

        double result = DataUtilities.calculateColumnTotal(values1, 1, new int[]{0, 1});
        assertEquals(15.0, result, 0.000001);
    }


    @Test
    public void testCalculateColumnTotalWithOutOfBoundsRows() {
        mockingContext.checking(new Expectations() {{
            one(values1).getRowCount();
            will(returnValue(2)); // Only 2 rows exist
            one(values1).getValue(0, 1);
            will(returnValue(5.0));
            one(values1).getValue(1, 1);
            will(returnValue(10.0));
        }});

        double result = DataUtilities.calculateColumnTotal(values1, 1, new int[]{0, 1, 3, 4}); // Rows 3, 4 are invalid
        assertEquals(15.0, result, 0.000001);
    }

    @Test
    public void testCalculateColumnTotalWithZeroRows() {
        mockingContext.checking(new Expectations() {{
            one(values1).getRowCount();
            will(returnValue(0)); // No data
        }});

        double result = DataUtilities.calculateColumnTotal(values1, 1, new int[]{0, 1});
        assertEquals(0.0, result, 0.000001);
    }
    

    @Test
    public void testCalculateRowTotalWithEmptyValidCols() {
        mockingContext.checking(new Expectations() {{
            one(values1).getColumnCount();
            will(returnValue(3));
        }});

        double result = DataUtilities.calculateRowTotal(values1, 1, new int[]{});
        assertEquals(0.0, result, 0.000001);
    }

    @Test
    public void testCalculateRowTotalWithValidCols() {
        mockingContext.checking(new Expectations() {{
            one(values1).getColumnCount();
            will(returnValue(3));
            one(values1).getValue(1, 0);
            will(returnValue(5.0));
            one(values1).getValue(1, 2);
            will(returnValue(10.0));
        }});

        double result = DataUtilities.calculateRowTotal(values1, 1, new int[]{0, 2});
        assertEquals(15.0, result, 0.000001);
    }

    @Test
    public void testCalculateRowTotalWithNullValues() {

        double result = DataUtilities.calculateRowTotal(values1, 1, new int[]{0, 1, 2});
        assertEquals(15.0, result, 0.000001);
    }
    

    @Test
    public void testCalculateRowTotalWithOutOfBoundsCols() {
        mockingContext.checking(new Expectations() {{
            one(values1).getColumnCount();
            will(returnValue(2)); // Only 2 columns exist
            one(values1).getValue(1, 0);
            will(returnValue(5.0));
            one(values1).getValue(1, 1);
            will(returnValue(10.0));
        }});

        double result = DataUtilities.calculateRowTotal(values1, 1, new int[]{0, 1, 3, 4}); // Cols 3, 4 are invalid
        assertEquals(15.0, result, 0.000001);
    }

    @Test
    public void testCalculateRowTotalWithZeroColumns() {
        mockingContext.checking(new Expectations() {{
            one(values1).getColumnCount();
            will(returnValue(0)); // No data
        }});

        double result = DataUtilities.calculateRowTotal(values1, 1, new int[]{0, 1});
        assertEquals(0.0, result, 0.000001);
    }

}