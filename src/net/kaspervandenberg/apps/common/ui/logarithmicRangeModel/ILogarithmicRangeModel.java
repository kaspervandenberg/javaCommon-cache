// © Kasper van den Berg 2009
package net.kaspervandenberg.apps.common.ui.logarithmicRangeModel;

import javax.swing.BoundedRangeModel;

/**
 * Adaption of {@link BoundedRangeModel} that allows logarithmically
 * scaling sliders etc.<b/>The interface adds a set of
 * get-/setUnconverted... to set minimum, maximum, and value. The
 * corresponding methods from BoundedRangeModel return or use
 * logarithms of these values.
 * 
 * @author Kasper van den Berg
 */
public interface ILogarithmicRangeModel extends BoundedRangeModel {

	/**
	 * @return the maximum, not converted to a logarithm
	 * @see #getMaximum()
	 */
	public abstract double getUnconvertedMaximum();

	/**
	 * return {@code toLog(getUnconvertedMaximum())}
	 * 
	 * @see #getUnconvertedMaximum()
	 * @see BoundedRangeModel#getMaximum()
	 */
	@Override
	public abstract int getMaximum();

	/**
	 * Set the maximum, not converted to a logarithm
	 * 
	 * @param newMaximum
	 *            the maximum value not converted to a logarithm
	 * @see #setMaximum(int)
	 */
	public abstract void setUnconvertedMaximum(double newMaximum);

	/**
	 * @param v
	 *            {@code toLog(newMaximum)}
	 * @see #setUnconvertedMaximum(double)
	 * @see BoundedRangeModel#setMaximum(int)
	 */
	@Override
	public abstract void setMaximum(int v);

	/**
	 * @return the minimum, not converted to a logarithm
	 * @see #getMinimum()
	 */
	public abstract double getUnconvertedMinimum();

	/**
	 * return {@code toLog(getUnconvertedMinimum())}
	 * 
	 * @see #getUnconvertedMinimum()
	 * @see BoundedRangeModel#getMinimum()
	 */
	@Override
	public abstract int getMinimum();

	/**
	 * Set the minimum, not converted to a logarithm
	 * 
	 * @param newMinimum
	 *            the minimum value not converted to a logarithm
	 * @see #setMinimum(int)
	 */
	public abstract void setUnconvertedMinimum(double newMinimum);

	/**
	 * @param v
	 *            {@code toLog(newMinimum)}
	 * @see #setUnconvertedMinimum(double)
	 * @see BoundedRangeModel#setMinimum(int)
	 */
	@Override
	public abstract void setMinimum(int v);

	/**
	 * @return this model's value, not converted to a logarithm
	 * @see #getValue()
	 */
	public abstract double getUnconvertedValue();

	/**
	 * @return {@code toLog(getUnconvertedValue())}
	 * @see #getUnconvertedValue()
	 * @see BoundedRangeModel#getValue()
	 */
	@Override
	public int getValue();

	/**
	 * @param newValue
	 *            this model's value, not converted to a logarithm
	 * @see #setValue(int)
	 */
	public abstract void setUnconvertedValue(double newValue);

	/**
	 * @param v
	 *            {@code toLog(newValue)}
	 * @see #setUnconvertedValue(double)
	 * @see BoundedRangeModel#setValue(int)
	 */
	@Override
	public void setValue(int v);

	/**
	 * @return the base of this logarithm
	 */
	public abstract double getBase();

	/**
	 * @param newBase
	 *            the base of this logarithm
	 */
	public abstract void setBase(double newBase);

	/**
	 * During conversion values are scaled by a factor. A higher
	 * factor means higher precision and less range (i.e. more {@code
	 * int}s fit within a range between two logarithm values
	 * 
	 * @return scaling factor
	 * @see #setFactor(double)
	 */
	public abstract double getFactor();

	/**
	 * @param newFactor
	 *            scaling factor
	 * @see #getFactor()
	 */
	public abstract void setFactor(double newFactor);

	/**
	 * @param value
	 * @return the {@code getBase()}-logarithm of {@code value}, scaled by {@code getFactor()}
	 * 
	 * @see #toValue(int)
	 * @see #getBase()
	 * @see #getFactor()
	 */
	public abstract int toLog(double value);

	/**
	 * @param log
	 * @return	{@code getBase()} to the power of ({@code log/getFactor()})
	 * 
	 * @see #toLog(double)
	 * @see #getBase()
	 * @see #getFactor()
	 */
	public abstract double toValue(int log);
}