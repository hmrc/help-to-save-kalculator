#import <Foundation/NSArray.h>
#import <Foundation/NSDictionary.h>
#import <Foundation/NSError.h>
#import <Foundation/NSObject.h>
#import <Foundation/NSSet.h>
#import <Foundation/NSString.h>
#import <Foundation/NSValue.h>

@class HTSKHtSSchemeConfig, HTSKCalculatorResponse, HTSKFinalBonusCalculatorResponse, HTSKFinalBonusInput, HTSKFirstBonusCalculatorResponse, HTSKFirstBonusInput, HTSKMonthlyBreakdown, HTSKFinalBonusStatus, HTSKYearMonthDayInput, HTSKKotlinEnum<E>, HTSKKotlinThrowable, HTSKKotlinArray<T>, HTSKKotlinException;

@protocol HTSKKotlinComparable, HTSKKotlinIterator;

NS_ASSUME_NONNULL_BEGIN
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunknown-warning-option"
#pragma clang diagnostic ignored "-Wincompatible-property-type"
#pragma clang diagnostic ignored "-Wnullability"

__attribute__((swift_name("KotlinBase")))
@interface HTSKBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end;

@interface HTSKBase (HTSKBaseCopying) <NSCopying>
@end;

__attribute__((swift_name("KotlinMutableSet")))
@interface HTSKMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end;

__attribute__((swift_name("KotlinMutableDictionary")))
@interface HTSKMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end;

@interface NSError (NSErrorHTSKKotlinException)
@property (readonly) id _Nullable kotlinException;
@end;

__attribute__((swift_name("KotlinNumber")))
@interface HTSKNumber : NSNumber
- (instancetype)initWithChar:(char)value __attribute__((unavailable));
- (instancetype)initWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
- (instancetype)initWithShort:(short)value __attribute__((unavailable));
- (instancetype)initWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
- (instancetype)initWithInt:(int)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
- (instancetype)initWithLong:(long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
- (instancetype)initWithLongLong:(long long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
- (instancetype)initWithFloat:(float)value __attribute__((unavailable));
- (instancetype)initWithDouble:(double)value __attribute__((unavailable));
- (instancetype)initWithBool:(BOOL)value __attribute__((unavailable));
- (instancetype)initWithInteger:(NSInteger)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
+ (instancetype)numberWithChar:(char)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
+ (instancetype)numberWithShort:(short)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
+ (instancetype)numberWithInt:(int)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
+ (instancetype)numberWithLong:(long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
+ (instancetype)numberWithLongLong:(long long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
+ (instancetype)numberWithFloat:(float)value __attribute__((unavailable));
+ (instancetype)numberWithDouble:(double)value __attribute__((unavailable));
+ (instancetype)numberWithBool:(BOOL)value __attribute__((unavailable));
+ (instancetype)numberWithInteger:(NSInteger)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
@end;

__attribute__((swift_name("KotlinByte")))
@interface HTSKByte : HTSKNumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end;

__attribute__((swift_name("KotlinUByte")))
@interface HTSKUByte : HTSKNumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end;

__attribute__((swift_name("KotlinShort")))
@interface HTSKShort : HTSKNumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end;

__attribute__((swift_name("KotlinUShort")))
@interface HTSKUShort : HTSKNumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end;

__attribute__((swift_name("KotlinInt")))
@interface HTSKInt : HTSKNumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end;

__attribute__((swift_name("KotlinUInt")))
@interface HTSKUInt : HTSKNumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end;

__attribute__((swift_name("KotlinLong")))
@interface HTSKLong : HTSKNumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end;

__attribute__((swift_name("KotlinULong")))
@interface HTSKULong : HTSKNumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end;

__attribute__((swift_name("KotlinFloat")))
@interface HTSKFloat : HTSKNumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end;

__attribute__((swift_name("KotlinDouble")))
@interface HTSKDouble : HTSKNumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end;

__attribute__((swift_name("KotlinBoolean")))
@interface HTSKBoolean : HTSKNumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end;

__attribute__((swift_name("HtSSchemeConfig")))
@interface HTSKHtSSchemeConfig : HTSKBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (readonly) int32_t endOfFirstBonusPeriod __attribute__((swift_name("endOfFirstBonusPeriod")));
@property (readonly) int32_t endOfSecondBonusPeriod __attribute__((swift_name("endOfSecondBonusPeriod")));
@property (readonly) int32_t startOfFirstBonusPeriod __attribute__((swift_name("startOfFirstBonusPeriod")));
@property (readonly) int32_t startOfSecondBonusPeriod __attribute__((swift_name("startOfSecondBonusPeriod")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Calculator")))
@interface HTSKCalculator : HTSKHtSSchemeConfig
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (instancetype)calculator __attribute__((swift_name("init()")));
- (HTSKCalculatorResponse *)runRegularPayment:(double)regularPayment __attribute__((swift_name("run(regularPayment:)")));
- (HTSKCalculatorResponse *)runRegularPayment:(double)regularPayment currentBalance:(double)currentBalance currentFirstPeriodBonus:(double)currentFirstPeriodBonus currentSecondPeriodBonus:(double)currentSecondPeriodBonus accountStartDate:(double)accountStartDate __attribute__((swift_name("run(regularPayment:currentBalance:currentFirstPeriodBonus:currentSecondPeriodBonus:accountStartDate:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FinalBonusTermCalculator")))
@interface HTSKFinalBonusTermCalculator : HTSKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)finalBonusTermCalculator __attribute__((swift_name("init()")));
- (HTSKFinalBonusCalculatorResponse *)runFinalBonusCalculatorInput:(HTSKFinalBonusInput *)input __attribute__((swift_name("runFinalBonusCalculator(input:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FirstBonusTermCalculator")))
@interface HTSKFirstBonusTermCalculator : HTSKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)firstBonusTermCalculator __attribute__((swift_name("init()")));
- (HTSKFirstBonusCalculatorResponse *)runFirstBonusCalculatorInput:(HTSKFirstBonusInput *)input __attribute__((swift_name("runFirstBonusCalculator(input:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RegularPaymentValidators")))
@interface HTSKRegularPaymentValidators : HTSKBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)regularPaymentValidators __attribute__((swift_name("init()")));
- (BOOL)isAboveMinimumRegularPaymentsPayment:(double)payment __attribute__((swift_name("isAboveMinimumRegularPayments(payment:)")));
- (BOOL)isBelowMaximumRegularPaymentsPayment:(double)payment __attribute__((swift_name("isBelowMaximumRegularPayments(payment:)")));
- (BOOL)isValidRegularPaymentsPayment:(double)payment __attribute__((swift_name("isValidRegularPayments(payment:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CalculatorResponse")))
@interface HTSKCalculatorResponse : HTSKBase
- (instancetype)initWithMonthlyPayments:(double)monthlyPayments monthlyBreakdown:(NSArray<HTSKMonthlyBreakdown *> *)monthlyBreakdown endOfSchemeSavings:(double)endOfSchemeSavings endOfPeriod1Bonus:(double)endOfPeriod1Bonus endOfPeriod1Savings:(double)endOfPeriod1Savings endOfPeriod2Bonus:(double)endOfPeriod2Bonus endOfPeriod2Savings:(double)endOfPeriod2Savings __attribute__((swift_name("init(monthlyPayments:monthlyBreakdown:endOfSchemeSavings:endOfPeriod1Bonus:endOfPeriod1Savings:endOfPeriod2Bonus:endOfPeriod2Savings:)"))) __attribute__((objc_designated_initializer));
- (double)component1 __attribute__((swift_name("component1()")));
- (NSArray<HTSKMonthlyBreakdown *> *)component2 __attribute__((swift_name("component2()")));
- (double)component3 __attribute__((swift_name("component3()")));
- (double)component4 __attribute__((swift_name("component4()")));
- (double)component5 __attribute__((swift_name("component5()")));
- (double)component6 __attribute__((swift_name("component6()")));
- (double)component7 __attribute__((swift_name("component7()")));
- (HTSKCalculatorResponse *)doCopyMonthlyPayments:(double)monthlyPayments monthlyBreakdown:(NSArray<HTSKMonthlyBreakdown *> *)monthlyBreakdown endOfSchemeSavings:(double)endOfSchemeSavings endOfPeriod1Bonus:(double)endOfPeriod1Bonus endOfPeriod1Savings:(double)endOfPeriod1Savings endOfPeriod2Bonus:(double)endOfPeriod2Bonus endOfPeriod2Savings:(double)endOfPeriod2Savings __attribute__((swift_name("doCopy(monthlyPayments:monthlyBreakdown:endOfSchemeSavings:endOfPeriod1Bonus:endOfPeriod1Savings:endOfPeriod2Bonus:endOfPeriod2Savings:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) double endOfPeriod1Bonus __attribute__((swift_name("endOfPeriod1Bonus")));
@property (readonly) double endOfPeriod1Savings __attribute__((swift_name("endOfPeriod1Savings")));
@property (readonly) double endOfPeriod1Total __attribute__((swift_name("endOfPeriod1Total")));
@property (readonly) double endOfPeriod2Bonus __attribute__((swift_name("endOfPeriod2Bonus")));
@property (readonly) double endOfPeriod2Savings __attribute__((swift_name("endOfPeriod2Savings")));
@property (readonly) double endOfPeriod2Total __attribute__((swift_name("endOfPeriod2Total")));
@property (readonly) double endOfSchemeBonus __attribute__((swift_name("endOfSchemeBonus")));
@property (readonly) double endOfSchemeSavings __attribute__((swift_name("endOfSchemeSavings")));
@property (readonly) double endOfSchemeTotal __attribute__((swift_name("endOfSchemeTotal")));
@property (readonly) NSArray<HTSKMonthlyBreakdown *> *monthlyBreakdown __attribute__((swift_name("monthlyBreakdown")));
@property (readonly) double monthlyPayments __attribute__((swift_name("monthlyPayments")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FinalBonusCalculatorResponse")))
@interface HTSKFinalBonusCalculatorResponse : HTSKBase
- (instancetype)initWithTotalProjectedSavingsIncludingBonuses:(double)totalProjectedSavingsIncludingBonuses totalProjectedSavings:(double)totalProjectedSavings totalProjectedBonuses:(double)totalProjectedBonuses finalBonusStatus:(HTSKFinalBonusStatus *)finalBonusStatus __attribute__((swift_name("init(totalProjectedSavingsIncludingBonuses:totalProjectedSavings:totalProjectedBonuses:finalBonusStatus:)"))) __attribute__((objc_designated_initializer));
- (double)component1 __attribute__((swift_name("component1()")));
- (double)component2 __attribute__((swift_name("component2()")));
- (double)component3 __attribute__((swift_name("component3()")));
- (HTSKFinalBonusStatus *)component4 __attribute__((swift_name("component4()")));
- (HTSKFinalBonusCalculatorResponse *)doCopyTotalProjectedSavingsIncludingBonuses:(double)totalProjectedSavingsIncludingBonuses totalProjectedSavings:(double)totalProjectedSavings totalProjectedBonuses:(double)totalProjectedBonuses finalBonusStatus:(HTSKFinalBonusStatus *)finalBonusStatus __attribute__((swift_name("doCopy(totalProjectedSavingsIncludingBonuses:totalProjectedSavings:totalProjectedBonuses:finalBonusStatus:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) HTSKFinalBonusStatus *finalBonusStatus __attribute__((swift_name("finalBonusStatus")));
@property (readonly) double totalProjectedBonuses __attribute__((swift_name("totalProjectedBonuses")));
@property (readonly) double totalProjectedSavings __attribute__((swift_name("totalProjectedSavings")));
@property (readonly) double totalProjectedSavingsIncludingBonuses __attribute__((swift_name("totalProjectedSavingsIncludingBonuses")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FinalBonusInput")))
@interface HTSKFinalBonusInput : HTSKBase
- (instancetype)initWithRegularPayment:(double)regularPayment currentBalance:(double)currentBalance paidInThisMonth:(double)paidInThisMonth canPayInThisMonth:(double)canPayInThisMonth thisMonthEndDate:(HTSKYearMonthDayInput *)thisMonthEndDate secondTermEndDate:(HTSKYearMonthDayInput *)secondTermEndDate balanceMustBeMoreThanForBonus:(double)balanceMustBeMoreThanForBonus secondTermBonusEstimate:(double)secondTermBonusEstimate __attribute__((swift_name("init(regularPayment:currentBalance:paidInThisMonth:canPayInThisMonth:thisMonthEndDate:secondTermEndDate:balanceMustBeMoreThanForBonus:secondTermBonusEstimate:)"))) __attribute__((objc_designated_initializer));
- (double)component1 __attribute__((swift_name("component1()")));
- (double)component2 __attribute__((swift_name("component2()")));
- (double)component3 __attribute__((swift_name("component3()")));
- (double)component4 __attribute__((swift_name("component4()")));
- (HTSKYearMonthDayInput *)component5 __attribute__((swift_name("component5()")));
- (HTSKYearMonthDayInput *)component6 __attribute__((swift_name("component6()")));
- (double)component7 __attribute__((swift_name("component7()")));
- (double)component8 __attribute__((swift_name("component8()")));
- (HTSKFinalBonusInput *)doCopyRegularPayment:(double)regularPayment currentBalance:(double)currentBalance paidInThisMonth:(double)paidInThisMonth canPayInThisMonth:(double)canPayInThisMonth thisMonthEndDate:(HTSKYearMonthDayInput *)thisMonthEndDate secondTermEndDate:(HTSKYearMonthDayInput *)secondTermEndDate balanceMustBeMoreThanForBonus:(double)balanceMustBeMoreThanForBonus secondTermBonusEstimate:(double)secondTermBonusEstimate __attribute__((swift_name("doCopy(regularPayment:currentBalance:paidInThisMonth:canPayInThisMonth:thisMonthEndDate:secondTermEndDate:balanceMustBeMoreThanForBonus:secondTermBonusEstimate:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) double balanceMustBeMoreThanForBonus __attribute__((swift_name("balanceMustBeMoreThanForBonus")));
@property (readonly) double canPayInThisMonth __attribute__((swift_name("canPayInThisMonth")));
@property (readonly) double currentBalance __attribute__((swift_name("currentBalance")));
@property (readonly) double paidInThisMonth __attribute__((swift_name("paidInThisMonth")));
@property (readonly) double regularPayment __attribute__((swift_name("regularPayment")));
@property (readonly) double secondTermBonusEstimate __attribute__((swift_name("secondTermBonusEstimate")));
@property (readonly) HTSKYearMonthDayInput *secondTermEndDate __attribute__((swift_name("secondTermEndDate")));
@property (readonly) HTSKYearMonthDayInput *thisMonthEndDate __attribute__((swift_name("thisMonthEndDate")));
@end;

__attribute__((swift_name("KotlinComparable")))
@protocol HTSKKotlinComparable
@required
- (int32_t)compareToOther:(id _Nullable)other __attribute__((swift_name("compareTo(other:)")));
@end;

__attribute__((swift_name("KotlinEnum")))
@interface HTSKKotlinEnum<E> : HTSKBase <HTSKKotlinComparable>
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer));
- (int32_t)compareToOther:(E)other __attribute__((swift_name("compareTo(other:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) int32_t ordinal __attribute__((swift_name("ordinal")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FinalBonusStatus")))
@interface HTSKFinalBonusStatus : HTSKKotlinEnum<HTSKFinalBonusStatus *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) HTSKFinalBonusStatus *earned __attribute__((swift_name("earned")));
@property (class, readonly) HTSKFinalBonusStatus *possibleToEarn __attribute__((swift_name("possibleToEarn")));
@property (class, readonly) HTSKFinalBonusStatus *cannotEarn __attribute__((swift_name("cannotEarn")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FirstBonusCalculatorResponse")))
@interface HTSKFirstBonusCalculatorResponse : HTSKBase
- (instancetype)initWithTotalProjectedSavingsIncludingBonuses:(double)totalProjectedSavingsIncludingBonuses totalProjectedSavings:(double)totalProjectedSavings totalProjectedBonuses:(double)totalProjectedBonuses projectedSavingsFirstBonusPeriod:(double)projectedSavingsFirstBonusPeriod projectedFirstBonus:(double)projectedFirstBonus projectedAdditionalSavingsFinalBonusPeriod:(double)projectedAdditionalSavingsFinalBonusPeriod projectedFinalBonus:(double)projectedFinalBonus __attribute__((swift_name("init(totalProjectedSavingsIncludingBonuses:totalProjectedSavings:totalProjectedBonuses:projectedSavingsFirstBonusPeriod:projectedFirstBonus:projectedAdditionalSavingsFinalBonusPeriod:projectedFinalBonus:)"))) __attribute__((objc_designated_initializer));
- (double)component1 __attribute__((swift_name("component1()")));
- (double)component2 __attribute__((swift_name("component2()")));
- (double)component3 __attribute__((swift_name("component3()")));
- (double)component4 __attribute__((swift_name("component4()")));
- (double)component5 __attribute__((swift_name("component5()")));
- (double)component6 __attribute__((swift_name("component6()")));
- (double)component7 __attribute__((swift_name("component7()")));
- (HTSKFirstBonusCalculatorResponse *)doCopyTotalProjectedSavingsIncludingBonuses:(double)totalProjectedSavingsIncludingBonuses totalProjectedSavings:(double)totalProjectedSavings totalProjectedBonuses:(double)totalProjectedBonuses projectedSavingsFirstBonusPeriod:(double)projectedSavingsFirstBonusPeriod projectedFirstBonus:(double)projectedFirstBonus projectedAdditionalSavingsFinalBonusPeriod:(double)projectedAdditionalSavingsFinalBonusPeriod projectedFinalBonus:(double)projectedFinalBonus __attribute__((swift_name("doCopy(totalProjectedSavingsIncludingBonuses:totalProjectedSavings:totalProjectedBonuses:projectedSavingsFirstBonusPeriod:projectedFirstBonus:projectedAdditionalSavingsFinalBonusPeriod:projectedFinalBonus:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) double projectedAdditionalSavingsFinalBonusPeriod __attribute__((swift_name("projectedAdditionalSavingsFinalBonusPeriod")));
@property (readonly) double projectedFinalBonus __attribute__((swift_name("projectedFinalBonus")));
@property (readonly) double projectedFirstBonus __attribute__((swift_name("projectedFirstBonus")));
@property (readonly) double projectedSavingsFirstBonusPeriod __attribute__((swift_name("projectedSavingsFirstBonusPeriod")));
@property (readonly) double totalProjectedBonuses __attribute__((swift_name("totalProjectedBonuses")));
@property (readonly) double totalProjectedSavings __attribute__((swift_name("totalProjectedSavings")));
@property (readonly) double totalProjectedSavingsIncludingBonuses __attribute__((swift_name("totalProjectedSavingsIncludingBonuses")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FirstBonusInput")))
@interface HTSKFirstBonusInput : HTSKBase
- (instancetype)initWithRegularPayment:(double)regularPayment currentBalance:(double)currentBalance paidInThisMonth:(double)paidInThisMonth thisMonthEndDate:(HTSKYearMonthDayInput *)thisMonthEndDate firstTermEndDate:(HTSKYearMonthDayInput *)firstTermEndDate secondTermEndDate:(HTSKYearMonthDayInput *)secondTermEndDate balanceMustBeMoreThanForBonus:(double)balanceMustBeMoreThanForBonus __attribute__((swift_name("init(regularPayment:currentBalance:paidInThisMonth:thisMonthEndDate:firstTermEndDate:secondTermEndDate:balanceMustBeMoreThanForBonus:)"))) __attribute__((objc_designated_initializer));
- (double)component1 __attribute__((swift_name("component1()")));
- (double)component2 __attribute__((swift_name("component2()")));
- (double)component3 __attribute__((swift_name("component3()")));
- (HTSKYearMonthDayInput *)component4 __attribute__((swift_name("component4()")));
- (HTSKYearMonthDayInput *)component5 __attribute__((swift_name("component5()")));
- (HTSKYearMonthDayInput *)component6 __attribute__((swift_name("component6()")));
- (double)component7 __attribute__((swift_name("component7()")));
- (HTSKFirstBonusInput *)doCopyRegularPayment:(double)regularPayment currentBalance:(double)currentBalance paidInThisMonth:(double)paidInThisMonth thisMonthEndDate:(HTSKYearMonthDayInput *)thisMonthEndDate firstTermEndDate:(HTSKYearMonthDayInput *)firstTermEndDate secondTermEndDate:(HTSKYearMonthDayInput *)secondTermEndDate balanceMustBeMoreThanForBonus:(double)balanceMustBeMoreThanForBonus __attribute__((swift_name("doCopy(regularPayment:currentBalance:paidInThisMonth:thisMonthEndDate:firstTermEndDate:secondTermEndDate:balanceMustBeMoreThanForBonus:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) double balanceMustBeMoreThanForBonus __attribute__((swift_name("balanceMustBeMoreThanForBonus")));
@property (readonly) double currentBalance __attribute__((swift_name("currentBalance")));
@property (readonly) HTSKYearMonthDayInput *firstTermEndDate __attribute__((swift_name("firstTermEndDate")));
@property (readonly) double paidInThisMonth __attribute__((swift_name("paidInThisMonth")));
@property (readonly) double regularPayment __attribute__((swift_name("regularPayment")));
@property (readonly) HTSKYearMonthDayInput *secondTermEndDate __attribute__((swift_name("secondTermEndDate")));
@property (readonly) HTSKYearMonthDayInput *thisMonthEndDate __attribute__((swift_name("thisMonthEndDate")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MonthlyBreakdown")))
@interface HTSKMonthlyBreakdown : HTSKBase
- (instancetype)initWithMonthNumber:(int32_t)monthNumber savingsToDate:(double)savingsToDate period1Bonus:(double)period1Bonus period2Bonus:(double)period2Bonus __attribute__((swift_name("init(monthNumber:savingsToDate:period1Bonus:period2Bonus:)"))) __attribute__((objc_designated_initializer));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (double)component2 __attribute__((swift_name("component2()")));
- (double)component3 __attribute__((swift_name("component3()")));
- (double)component4 __attribute__((swift_name("component4()")));
- (HTSKMonthlyBreakdown *)doCopyMonthNumber:(int32_t)monthNumber savingsToDate:(double)savingsToDate period1Bonus:(double)period1Bonus period2Bonus:(double)period2Bonus __attribute__((swift_name("doCopy(monthNumber:savingsToDate:period1Bonus:period2Bonus:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) double bonusToDate __attribute__((swift_name("bonusToDate")));
@property (readonly) int32_t monthNumber __attribute__((swift_name("monthNumber")));
@property (readonly) double period1Bonus __attribute__((swift_name("period1Bonus")));
@property (readonly) double period2Bonus __attribute__((swift_name("period2Bonus")));
@property (readonly) double savingsToDate __attribute__((swift_name("savingsToDate")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("YearMonthDayInput")))
@interface HTSKYearMonthDayInput : HTSKBase
- (instancetype)initWithYear:(int32_t)year month:(int32_t)month day:(int32_t)day __attribute__((swift_name("init(year:month:day:)"))) __attribute__((objc_designated_initializer));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (int32_t)component2 __attribute__((swift_name("component2()")));
- (int32_t)component3 __attribute__((swift_name("component3()")));
- (HTSKYearMonthDayInput *)doCopyYear:(int32_t)year month:(int32_t)month day:(int32_t)day __attribute__((swift_name("doCopy(year:month:day:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t day __attribute__((swift_name("day")));
@property (readonly) int32_t month __attribute__((swift_name("month")));
@property (readonly) int32_t year __attribute__((swift_name("year")));
@end;

__attribute__((swift_name("KotlinThrowable")))
@interface HTSKKotlinThrowable : HTSKBase
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(HTSKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(HTSKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (HTSKKotlinArray<NSString *> *)getStackTrace __attribute__((swift_name("getStackTrace()")));
- (void)printStackTrace __attribute__((swift_name("printStackTrace()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) HTSKKotlinThrowable * _Nullable cause __attribute__((swift_name("cause")));
@property (readonly) NSString * _Nullable message __attribute__((swift_name("message")));
@end;

__attribute__((swift_name("KotlinException")))
@interface HTSKKotlinException : HTSKKotlinThrowable
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(HTSKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(HTSKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("InvalidRegularPaymentException")))
@interface HTSKInvalidRegularPaymentException : HTSKKotlinException
- (instancetype)initWithRegularPayment:(double)regularPayment __attribute__((swift_name("init(regularPayment:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(HTSKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithCause:(HTSKKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinArray")))
@interface HTSKKotlinArray<T> : HTSKBase
+ (instancetype)arrayWithSize:(int32_t)size init:(T _Nullable (^)(HTSKInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (T _Nullable)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (id<HTSKKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(T _Nullable)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end;

__attribute__((swift_name("KotlinIterator")))
@protocol HTSKKotlinIterator
@required
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (id _Nullable)next __attribute__((swift_name("next()")));
@end;

#pragma clang diagnostic pop
NS_ASSUME_NONNULL_END
