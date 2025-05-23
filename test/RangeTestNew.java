package org.jfree.data;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.jfree.data.Range; 
import org.junit.*;

public class RangeTestNew{

	private Range exampleRange1;
    private Range exampleRange2;
    private Range exampleRange3;
    private Range exampleRange4;
    private Range exampleRange5;
    private Range contains_range;

    
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { 
    	exampleRange1 = new Range(-2, 3);
    	exampleRange2 = null;
    	exampleRange3 = null;
    	exampleRange4 = new Range(2, 7);
    	exampleRange5 = new Range(12, 17);
        contains_range = new Range(-5.0, 5.0);
    }

// Test cases for combine
    @Test
    public void testCombineWithNullRange1() {
        assertEquals("The range should be equal to exampleRange1",
        exampleRange1, Range.combine(exampleRange1, exampleRange2));
    }
    @Test
    public void testCombineWithNullRange2() {
        assertEquals("The range should be equal to exampleRange1",
        exampleRange1, Range.combine(exampleRange2, exampleRange1));
    }
    
    @Test
    public void testCombineWithBothNullRanges() {
        assertEquals("The range should be null",
        null, Range.combine(exampleRange2, exampleRange3));
    }
    
    @Test
    public void testCombineWithOverlappingRanges() {
        Range test = Range.combine(exampleRange4, exampleRange1);
    	assertEquals("The range should be equal to [-2,7]", new Range(-2,7), test);
    }
    
    @Test
    public void testCombineWithDisjointRanges() {
        Range test = Range.combine(exampleRange5, exampleRange4);
    	assertEquals("The range should be equal to [2,17]", new Range(2,17), test);
    }
    
  //Test cases for ToString()
 	 
	    @Test
	    public void testToStringWithPositiveNumbers() {
	        Range range = new Range(5.0, 10.0);
	        String expected = "Range[5.0,10.0]";
	        assertEquals("toString should return Range[lower,upper]", expected, range.toString());
	    }
	    
	    @Test
	    public void testToStringWithNegativeNumbers() {
	        Range range = new Range(-2.0, -1.0);
	        String expected = "Range[-2.0,-1.0]";
	        assertEquals("toString should correctly format negative values", expected, range.toString());
	    }
	    
	    @Test
	    public void testToStringWithSameNumbers() {
	        Range range = new Range(1.0, 1.0);
	        String expected = "Range[1.0,1.0]";
	        assertEquals("toString should correctly format negative values", expected, range.toString());
	    }
	    
	    @Test
	    public void testToStringWithZero() {
	        Range range = new Range(0.0, 0.0);
	        String expected = "Range[0.0,0.0]";
	        assertEquals("toString should handle zero values", expected, range.toString());
	    }
	    
	    @Test
	    public void testToStringWithMixedValues() {
	        Range range = new Range(-1.0, 1.0);
	        String expected = "Range[-1.0,1.0]";
	        assertEquals("toString should correctly display mixed values", expected, range.toString());
	    }
	    
	  //Tests for UpperBound

		//Declaring and setting up variables
		private Range finiteRangeUpper;
	    private Range infiniteRangeUpper;
	    private Range nanRangeUpper;

	    @Before
	    public void setUpUpper() throws Exception {
	        // Creating different range scenarios
	        finiteRangeUpper = new Range(-10.0, 15.5); // Finite range
	        infiniteRangeUpper = new Range(0.0, Double.POSITIVE_INFINITY); // Infinite upper bound
	        nanRangeUpper = new Range(5.0, Double.NaN); // NaN upper bound
	    }

		//Test for checking upperbound when range has a finite range
	    @Test
	    public void testGetUpperBoundForFiniteValue() {
	        // Upper bound should be 15.5
	        assertEquals(15.5, finiteRangeUpper.getUpperBound(), 0.000000001d);
	    }

		//test for checking upperbound when range has a positive infinity
	    @Test
	    public void testGetUpperBoundForInfiniteValue() {
	        // Upper bound should be POSITIVE_INFINITY
	        assertEquals(Double.POSITIVE_INFINITY, infiniteRangeUpper.getUpperBound(), 0.0);
	    }

		//test for checking upperbound when range has a NaN
	    @Test
	    public void testGetUpperBoundForNaNValue() {
	        // Upper bound should be NaN
	        assertTrue(Double.isNaN(nanRangeUpper.getUpperBound()));
	    }
	    
	    @Test
        public void testGetUpperBound_InvalidStateUsingReflection()
        		throws Exception {
            Range range = new Range(5, 10); 

            Field lowerField = Range.class.getDeclaredField("lower");
            Field upperField = Range.class.getDeclaredField("upper");
            lowerField.setAccessible(true);
            upperField.setAccessible(true);
            lowerField.setDouble(range, 15.0);
            upperField.setDouble(range, 10.0); // Now lower > upper

            try {
                range.getUpperBound();
                fail("Expected IllegalArgumentException due to"
                		+ "manipulated state.");
            } catch (IllegalArgumentException e) {
                assertEquals("Range(double, double): require lower"
                		+ " (15.0) <= upper (10.0).", e.getMessage());
            }
        }


		//Tests for LowerBound

		//declaring and setting up variables
		private Range finiteRangeLower;
		private Range infiniteRangeLower;
		private Range nanRangeLower;

		@Before
		public void setUpLower() throws Exception {
			// Creating different range scenarios
			finiteRangeLower = new Range(-10.0, 15.5); // Finite range
			infiniteRangeLower = new Range(Double.NEGATIVE_INFINITY, 100.0); // Infinite lower bound
			nanRangeLower = new Range(Double.NaN, 20.0); // NaN lower bound
		}

		//Test for checking lowerbound when range has a finite range
		@Test
		public void testGetLowerBoundForFiniteValue() {
			// Lower bound should be -10.0
			assertEquals(-10.0, finiteRangeLower.getLowerBound(), 0.000000001d);
		}

		//Test for checking lowerbound when range has a negative infinity 
		@Test
		public void testGetLowerBoundForInfiniteValue() {
			// Lower bound should be NEGATIVE_INFINITY
			assertEquals(Double.NEGATIVE_INFINITY, infiniteRangeLower.getLowerBound(), 0.0);
		}

		//Test for checking lowerbound when range has a NaN value
		@Test
		public void testGetLowerBoundForNaNValue() {
			// Lower bound should be NaN
			assertTrue(Double.isNaN(nanRangeLower.getLowerBound()));
		}
		@Test
		public void testGetLowerBound_InvalidStateUsingReflection() throws Exception {
		    Range range = new Range(5, 10); // Initially valid

		    // Force invalid state using reflection
		    Field lowerField = Range.class.getDeclaredField("lower");
		    Field upperField = Range.class.getDeclaredField("upper");
		    lowerField.setAccessible(true);
		    upperField.setAccessible(true);
		    lowerField.setDouble(range, 15.0);
		    upperField.setDouble(range, 10.0); // Now lower > upper

		    try {
		        range.getLowerBound();
		        fail("Expected IllegalArgumentException due to manipulated state.");
		    } catch (IllegalArgumentException e) {
		        assertEquals("Range(double, double): require lower (15.0) <= upper (10.0).", e.getMessage());
		    }
		}

	   
		@Test
		public void testContains_WithReflectionForFullCoverage() throws Exception {
		    Range range = new Range(5, 10); // valid initial state

		    // Force invalid internal state using reflection
		    Field lowerField = Range.class.getDeclaredField("lower");
		    Field upperField = Range.class.getDeclaredField("upper");
		    lowerField.setAccessible(true);
		    upperField.setAccessible(true);

		    lowerField.setDouble(range, 15.0);
		    upperField.setDouble(range, 10.0); // invalid: lower > upper

		    // This value would normally be between lower and upper if the range were valid
		    double testValue = 12.0;

		    // Act & Assert
		    assertFalse("Should return false due to invalid internal state.", range.contains(testValue));
		}

		//Tests for contains()
		
		@Test
	       // Test for value within range
	    public void testValueWithinRange() {
	        assertTrue("Value within range should return true", contains_range.contains(0.0));
	    }

	    @Test
	    // Test for value at lower bound
	    public void testValueAtLowerBound() {
	        assertTrue("Value at lower bound should return true", contains_range.contains(-5.0));
	    }

	    @Test
	    // Test for value at upper bound
	    public void testValueAtUpperBound() {
	        assertTrue("Value at upper bound should return true", contains_range.contains(5.0));
	    }

	    @Test
	    // Test for value below range
	    public void testValueBelowRange() {
	        assertFalse("Value below range should return false", contains_range.contains(-5.1));
	    }

	    @Test
	    // Test for value above range
	    public void testValueAboveRange() {
	        assertFalse("Value above range should return false", contains_range.contains(5.001));
	    }

	    @Test
	    // Test for negative range
	    public void testNegativeRange() {
	        Range negativeRange = new Range(-10.0, -1.0);
	        assertTrue("Value within negative range should return true", negativeRange.contains(-5.0));
	        assertFalse("Value above negative range should return false", negativeRange.contains(0.0));
	    }

	    @Test
	    // Test for positive range
	    public void testPositiveRange() {
	        Range zeroRange = new Range(0.0, 10.0);
	        assertTrue("Value at zero boundary should return true", zeroRange.contains(4));
	        assertFalse("Value below zero boundary should return false", zeroRange.contains(-0.0000000001));
	    }
	    

	    // Test with lower and upper being negative: input: lower = -20, upper = -10
	    @Test
	    public void getLengthWithBothBoundsNegative() {
	        Range range = new Range(-20, -10);
	        double expected = 10.0; // (-10) - (-20) = 10
	        assertEquals("getLength() with both negative bounds should return 10.0", expected, range.getLength(), 0.000001);
	    }
	    
	    // Test with lower and upper being positive: input: lower = 10, upper = 20
	    @Test
	    public void getLengthWithBothBoundsPositive() {
	        Range range = new Range(10, 20);
	        double expected = 10.0; // 20 - 10 = 10
	        assertEquals("getLength() with both positive bounds should return 10.0", expected, range.getLength(), 0.000001);
	    }
	    
	    // Test with lower and upper being equal: input: lower = 0, upper = 0
	    @Test
	    public void getLengthWithBothBoundsEqual() {
	        Range range = new Range(0, 0);
	        double expected = 0.0; // 0 - 0 = 0
	        assertEquals("getLength() with equal bounds should return 0.0", expected, range.getLength(), 0.000001);
	    }
	    
	    // Test with lower < 0 and upper > 0: input: lower = -10, upper = 20
	    @Test
	    public void getLengthWithNegativeLowerAndPositiveUpper() {
	        Range range = new Range(-10, 20);
	        double expected = 30.0; // 20 - (-10) = 30
	        assertEquals("getLength() with negative lower and positive upper should return 30.0", expected, range.getLength(), 0.000001);
	    }
	    
	    // Test with lower = 0 and upper > 0: input: lower = 0, upper = 20
	    @Test
	    public void getLengthWithZeroLowerAndPositiveUpper() {
	        Range range = new Range(0, 20);
	        double expected = 20.0; // 20 - 0 = 20
	        assertEquals("getLength() with zero lower and positive upper should return 20.0", expected, range.getLength(), 0.000001);
	    }
	    
	    // Test with lower < 0 and upper = 0: input: lower = -10, upper = 0
	    @Test
	    public void getLengthWithNegativeLowerAndZeroUpper() {
	        Range range = new Range(-10, 0);
	        double expected = 10.0; // 0 - (-10) = 10
	        assertEquals("getLength() with negative lower and zero upper should return 10.0", expected, range.getLength(), 0.000001);
	    }

	    // Test case for invalid range where lower > upper
	    @Test
	    public void getLengthWithInvalidRange() {
	        try {
	            new Range(10, -10);
	        } catch (IllegalArgumentException e) {
	            String expectedMessage = "Range(double, double): require lower (10.0) <= upper (-10.0).";
	            assertEquals(expectedMessage, e.getMessage());
	        }
	    }

	@After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
    
    
    //Test for getCentralValue()
    @Test
    public void testGetCentralValue() {
        Range range = new Range(5.0, 15.0);
        
        double centralValue = range.getCentralValue();
        
        assertEquals(10.0, centralValue, 0.000000001); 
    }
    
    //Tests for constrain
    
    @Test
    public void testConstrainWithinRange() {
        Range range = new Range(5.0, 10.0); 
        double result = range.constrain(7.0); 
        assertEquals(7.0, result, 0.000000001); 
    }
    
    @Test
    public void testConstrainAboveUpperBound() {
        Range range = new Range(5.0, 10.0); 
        double result = range.constrain(15.0);
        assertEquals(10.0, result, 0.000000001); 
    }

    @Test
    public void testConstrainBelowLowerBound() {
        Range range = new Range(5.0, 10.0); 
        double result = range.constrain(3.0); 
        assertEquals(5.0, result, 0.000000001);
    }
    
    //Tests for intersects(double,double)
    @Test
    public void testIntersectsLowerBoundaryTrue() {
        Range range = new Range(5.0, 10.0);
        boolean result = range.intersects(4.0, 6.0);
        assertTrue(result);
    }

    @Test
    public void testIntersectsLowerBoundaryFalse() {
        Range range = new Range(5.0, 10.0);
        boolean result = range.intersects(4.0, 5.0);
        assertFalse(result);
    }

    @Test
    public void testIntersectsWithinRangeTrue() {
        Range range = new Range(5.0, 10.0);
        boolean result = range.intersects(6.0, 8.0);
        assertTrue(result);
    }

    @Test
    public void testIntersectsOutsideRangeFalse() {
        Range range = new Range(5.0, 10.0);
        boolean result = range.intersects(11.0, 12.0);
        assertFalse(result);
    }
    
    //Test for intersects(Range)
    @Test
    public void testIntersectsRange() {
        Range range1 = new Range(5.0, 10.0);
        Range range2 = new Range(8.0, 12.0);
        boolean result = range1.intersects(range2);
        assertTrue(result);
    }
    
    //Test for hashcode
    @Test
    public void testHashCodeWithDistinctBounds() {
        Range range = new Range(5.0, 10.0);
        int hash = range.hashCode();
        assertNotNull(hash);
    }
    
    @Test
    public void testHashCodeWithEqualBounds() {
        Range range = new Range(5.0, 5.0);
        int hash = range.hashCode();
        assertNotNull(hash);
    }
    
    //Tests for equals
    @Test
    public void testEqualsWithNonRangeObject() {
        Object nonRangeObject = "Not a Range";
        Range range = new Range(1.0, 5.0);
        assertFalse(range.equals(nonRangeObject));
    }

    @Test
    public void testEqualsWithDifferentLowerBound() {
        Range range1 = new Range(1.0, 5.0);
        Range range2 = new Range(2.0, 5.0);
        assertFalse(range1.equals(range2));
    }

    @Test
    public void testEqualsWithDifferentUpperBound() {
        Range range1 = new Range(1.0, 5.0);
        Range range2 = new Range(1.0, 6.0);
        assertFalse(range1.equals(range2));
    }

    @Test
    public void testEqualsWithEqualRanges() {
        Range range1 = new Range(1.0, 5.0);
        Range range2 = new Range(1.0, 5.0);
        assertTrue(range1.equals(range2));
    }
    
    //Test getLength
    @Test
	public void testGetLength_WithReflectionForFullCoverage() throws Exception {
	    Range range = new Range(5, 10); // valid initial state

	    // Force invalid internal state using reflection
	    Field lowerField = Range.class.getDeclaredField("lower");
	    Field upperField = Range.class.getDeclaredField("upper");
	    lowerField.setAccessible(true);
	    upperField.setAccessible(true);

	    lowerField.setDouble(range, 15.0);
	    upperField.setDouble(range, 10.0); // invalid: lower > upper

	    try {
	        range.getLength();
	        fail("Expected IllegalArgumentException due to manipulated state.");
	    } catch (IllegalArgumentException e) {
	        assertEquals("Range(double, double): require lower (15.0) <= upper (10.0).", e.getMessage());
	    }
	}
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


@Test
public void combineIgnoringNaN_bothRangesNull() {
assertNull(Range.combineIgnoringNaN(null, null));
}

@Test
public void combineIgnoringNaN_firstRangeNullSecondNaN() {
Range r2 = new Range(Double.NaN, Double.NaN);
assertNull(Range.combineIgnoringNaN(null, r2));
}

@Test
public void combineIgnoringNaN_firstRangeNullSecondValid() {
Range r2 = new Range(1, 5);
assertEquals(r2, Range.combineIgnoringNaN(null, r2));
}

@Test
public void combineIgnoringNaN_secondRangeNullFirstNaN() {
Range r1 = new Range(Double.NaN, Double.NaN);
assertNull(Range.combineIgnoringNaN(r1, null));
}

@Test
public void combineIgnoringNaN_secondRangeNullFirstValid() {
Range r1 = new Range(2, 6);
assertEquals(r1, Range.combineIgnoringNaN(r1, null));
}

@Test
public void combineIgnoringNaN_bothRangesValid() {
Range r1 = new Range(1, 4);
Range r2 = new Range(2, 6);
assertEquals(new Range(1, 6), Range.combineIgnoringNaN(r1, r2));
}

@Test
public void combineIgnoringNaN_bothRangesNaN() {
Range r1 = new Range(Double.NaN, Double.NaN);
Range r2 = new Range(Double.NaN, Double.NaN);
assertNull(Range.combineIgnoringNaN(r1, r2));
}

@Test
public void combineIgnoringNaN_firstRangeNaNLowerBoundSecondValid() {
Range r1 = new Range(Double.NaN, 3);
Range r2 = new Range(2, 5);
assertEquals(new Range(2, 5), Range.combineIgnoringNaN(r1, r2));
}

@Test
public void combineIgnoringNaN_firstRangeNaNUpperBoundSecondValid() {
Range r1 = new Range(1, Double.NaN);
Range r2 = new Range(2, 5);
assertEquals(new Range(1, 5), Range.combineIgnoringNaN(r1, r2));
}

////////////////////////////////////////////////////////////////////////////////////////////


@Test
public void expand_validRangeNoMargins() {
Range range = new Range(2, 6);
assertEquals(new Range(2, 6), Range.expand(range, 0, 0));
}

@Test
public void expand_validRangePositiveMargins() {
Range range = new Range(2, 6);
assertEquals(new Range(0, 8), Range.expand(range, 0.5, 0.5));
}

@Test
public void expand_validRangeNegativeMargins() {
Range range = new Range(2, 6);
assertEquals(new Range(3, 5), Range.expand(range, -0.25, -0.25));
}

@Test
public void expand_validRangeMixedMargins() {
Range range = new Range(2, 6);
assertEquals(new Range(1, 7), Range.expand(range, 0.25, 0.25));
}

@Test
public void expand_rangeCollapsesLowerGreaterThanUpper() {
Range range = new Range(2, 6);
Range result = Range.expand(range, -1.5, -1.5); // Will collapse
assertEquals(new Range(4, 4), result);
}

@Test
public void expand_nullRangeThrowsException() {
try {
Range.expand(null, 0.5, 0.5);
fail("Expected an IllegalArgumentException but none was thrown.");
} catch (IllegalArgumentException e) {
assertEquals("Null 'range' argument.", e.getMessage());
}
}


///////////////////////////////////////////////////////////////////////////////////////////////////////

@Test
public void expandToInclude_nullRange() {
Range result = Range.expandToInclude(null, 5);
assertEquals(new Range(5, 5), result);
}

@Test
public void expandToInclude_valueLessThanLowerBound() {
Range range = new Range(3, 7);
Range result = Range.expandToInclude(range, 1);
assertEquals(new Range(1, 7), result);
}

@Test
public void expandToInclude_valueGreaterThanUpperBound() {
Range range = new Range(3, 7);
Range result = Range.expandToInclude(range, 10);
assertEquals(new Range(3, 10), result);
}

@Test
public void expandToInclude_valueWithinRange() {
Range range = new Range(3, 7);
Range result = Range.expandToInclude(range, 5);
assertEquals(range, result);
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////

@Test
public void maxtest() {
Range r1 = new Range(2, 5);
Range r2 = new Range(1, Double.NaN);
assertEquals(new Range(1, 5), Range.combineIgnoringNaN(r1, r2));
}

@Test
public void mintest() {
Range r1 = new Range(1, 555);
Range r2 = new Range(Double.NaN, 5);
assertEquals(new Range(1, 555), Range.combineIgnoringNaN(r1, r2));
}

////////////////////////////////////////////////////////////////////////////////////////////////////////


@Test
public void scale_nullBaseThrowsException() {
try {
Range.scale(null, 2.0);
fail("Expected an IllegalArgumentException but none was thrown.");
} catch (IllegalArgumentException e) {
assertEquals("Null 'base' argument.", e.getMessage());
}
}

@Test
public void scale_negativeFactorThrowsException() {
try {
Range.scale(new Range(2, 5), -1.0);
fail("Expected an IllegalArgumentException but none was thrown.");
} catch (IllegalArgumentException e) {
assertEquals("Negative 'factor' argument.", e.getMessage());
}
}

@Test
public void scale_zeroFactorReturnsZeroRange() {
Range base = new Range(2, 5);
Range result = Range.scale(base, 0);
assertEquals(new Range(0, 0), result);
}

@Test
public void scale_positiveFactorReturnsScaledRange() {
Range base = new Range(2, 5);
Range result = Range.scale(base, 2);
assertEquals(new Range(4, 10), result);
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


@Test
public void shift_validRangeShiftsCorrectly() {
Range base = new Range(2, 5);
Range result = Range.shift(base, 3);
assertEquals(new Range(5, 8), result);
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Test
public void shift_allowZeroCrossingTrue_shiftsRange() {
Range base = new Range(-5, 10);
Range result = Range.shift(base, 3, true);
assertEquals(new Range(-2, 13), result);
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////


@Test
public void shift_allowZeroCrossingTrue_negativeShift() {
Range base = new Range(0, 6);
Range result = Range.shift(base, 0, false);
assertEquals(new Range(0, 6), result);
}

@Test
public void shift_allowZeroCrossingTrue_zeroShift() {
Range base = new Range(-4, 8);
Range result = Range.shift(base, 0, false);
assertEquals(new Range(-4, 8), result);
}





}
