package net.kaspervandenberg.apps.common.ui.logarithmicRangeModel;

import javax.swing.BoundedRangeModel;

public interface ILogarithmicRangeModel extends BoundedRangeModel {

	public abstract double getUnconvertedMaximum();
	
	public abstract void setUnconvertedMaximum(double newMaximum);

	public abstract double getUnconvertedMinimum();

	public abstract void setUnconvertedMinimum(double newMinimum);
	
	public abstract double getUnconvertedValue();
	
	public abstract void setUnconvertedValue(double newValue);

	public abstract double getBase();

	public abstract void setBase(double newBase);

	public abstract double getFactor();

	public abstract void setFactor(double newFactor);

	public abstract int toLog(double value);

	public abstract double toValue(int log);

}