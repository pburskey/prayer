package com.burskey.validation;

import static java.lang.String.format;

public class IntegerValidationHelpers {



	public static Validation<Integer> lowerThan(int max){
		return SimpleValidation.from((i) -> i < max, format("must be lower than %s.", max));
	}
	
	public static Validation<Integer> greaterThan(int min){
		return SimpleValidation.from((i) -> i > min, format("must be greater than %s.", min));
	}
	
	public static Validation<Integer> intBetween(int min, int max){
		return greaterThan(min).and(lowerThan(max));
	}


	public static Validation<Integer> intEqualTo(int anInt)
	{
		Validation<Integer> aValidation = SimpleValidation.from((i) -> i == anInt, format("must be equal to %s.", anInt));
		return aValidation;
	}

}
