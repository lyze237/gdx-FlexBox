package facebook.yoga;

import static facebook.GlobalMembers.YGFloatArrayEqual;
import static facebook.GlobalMembers.YGUndefined;
import static facebook.yoga.GlobalMembers.isUndefined;
import static facebook.yoga.detail.GlobalMembers.bitWidthFn;
import static facebook.yoga.detail.GlobalMembers.getBooleanData;
import static facebook.yoga.detail.GlobalMembers.getEnumData;
import static facebook.yoga.detail.GlobalMembers.setBooleanData;
import static facebook.yoga.detail.GlobalMembers.setEnumData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class YGLayout {

    // This value was chosen based on empirical data:
    // 98% of analyzed layouts require less than 8 entries.
    public static final int YG_MAX_CACHED_RESULT_COUNT = 8;
    private static final int directionOffset = 0;
    private static final int didUseLegacyFlagOffset = directionOffset + bitWidthFn(YGDirection.class);
    private static final int doesLegacyStretchFlagAffectsLayoutOffset = didUseLegacyFlagOffset + 1;
    private static final int hadOverflowOffset = doesLegacyStretchFlagAffectsLayoutOffset + 1;
    public @NotNull ArrayList<Float> position = createEmptyFloatArray();
    public @NotNull ArrayList<Float> dimensions = new ArrayList<>(Arrays.asList(YGUndefined, YGUndefined));
    public @NotNull ArrayList<Float> margin = createEmptyFloatArray();
    public @NotNull ArrayList<Float> border = createEmptyFloatArray();
    public @NotNull ArrayList<Float> padding = createEmptyFloatArray();
    public int computedFlexBasisGeneration = 0;
    public YGFloatOptional computedFlexBasis = new YGFloatOptional();
    public int generationCount = 0;
    public YGDirection lastOwnerDirection = YGDirection.YGDirectionInherit;
    public int nextCachedMeasurementsIndex = 0;
    public @NotNull ArrayList<YGCachedMeasurement> cachedMeasurements = new ArrayList<>(YG_MAX_CACHED_RESULT_COUNT);
    public @NotNull ArrayList<Float> measuredDimensions = new ArrayList<>(Arrays.asList(YGUndefined, YGUndefined));
    public @NotNull YGCachedMeasurement cachedLayout = new YGCachedMeasurement();
    private byte flags = 0;

    public YGLayout() {
        for (int i = 0; i < YG_MAX_CACHED_RESULT_COUNT; i++) {
            cachedMeasurements.add(new YGCachedMeasurement());
        }
    }

    @NotNull
    private ArrayList<Float> createEmptyFloatArray() {
        return new ArrayList<Float>(List.of(0f, 0f, 0f, 0f));
    }

    //C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: YGDirection direction() const
    public final YGDirection direction() {
        return getEnumData(YGDirection.class, flags, directionOffset);
    }

    public final void setDirection(@NotNull YGDirection direction) {
        flags = setEnumData(YGDirection.class, flags, directionOffset, direction);
    }

    //C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: boolean didUseLegacyFlag() const
    public final boolean didUseLegacyFlag() {
        return getBooleanData(flags, didUseLegacyFlagOffset);
    }

    public final void setDidUseLegacyFlag(boolean val) {
        flags = setBooleanData(flags, didUseLegacyFlagOffset, val);
    }

    //C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: boolean doesLegacyStretchFlagAffectsLayout() const
    public final boolean doesLegacyStretchFlagAffectsLayout() {
        return getBooleanData(flags, doesLegacyStretchFlagAffectsLayoutOffset);
    }

    public final void setDoesLegacyStretchFlagAffectsLayout(boolean val) {
        flags = setBooleanData(flags, doesLegacyStretchFlagAffectsLayoutOffset, val);
    }

    //C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: boolean hadOverflow() const
    public final boolean hadOverflow() {
        return getBooleanData(flags, hadOverflowOffset);
    }

    public final void setHadOverflow(boolean hadOverflow) {
        flags = setBooleanData(flags, hadOverflowOffset, hadOverflow);
    }

    //C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: boolean operator ==(YGLayout layout) const
    public boolean equalsTo(@NotNull YGLayout layout) //Method definition originates from: YGLayout.cpp
    {
        boolean isEqual = YGFloatArrayEqual(position, layout.position) && YGFloatArrayEqual(dimensions,
                layout.dimensions) && YGFloatArrayEqual(margin, layout.margin) && YGFloatArrayEqual(border,
                layout.border) && YGFloatArrayEqual(padding,
                layout.padding) && direction() == layout.direction() && hadOverflow() == layout.hadOverflow() && lastOwnerDirection == layout.lastOwnerDirection && nextCachedMeasurementsIndex == layout.nextCachedMeasurementsIndex && cachedLayout.equalsTo(
                layout.cachedLayout) && computedFlexBasis == layout.computedFlexBasis;

        for (int i = 0; i < YG_MAX_CACHED_RESULT_COUNT && isEqual; ++i) { //TODO: Verify if this is correct
            isEqual = cachedMeasurements.get(i).equalsTo(layout.cachedMeasurements.get(i));
        }

        if (!isUndefined(measuredDimensions.get(0)) || !isUndefined(layout.measuredDimensions.get(0))) {
            isEqual = isEqual && (Objects.equals(measuredDimensions.get(0), layout.measuredDimensions.get(0)));
        }
        if (!isUndefined(measuredDimensions.get(1)) || !isUndefined(layout.measuredDimensions.get(1))) {
            isEqual = isEqual && (Objects.equals(measuredDimensions.get(1), layout.measuredDimensions.get(1)));
        }

        return isEqual;
    }

    public boolean notEqualsTo(@NotNull YGLayout layout) {
        return !(this.equalsTo(layout));
    }
}
