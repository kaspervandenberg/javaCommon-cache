package net.kaspervandenberg.apps.common.ui.logarithmicRangeModel;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;


public class LogarithmicRangeModel implements ILogarithmicRangeModel {
	private EventListenerList listeners = new EventListenerList();
	private double minimum = 0.0;
	private double maximum = 0.0;
	private double base = 1.0;
	private double factor = 1.0;
	private double value = 1.0;
	private boolean isAdjusting = false;
	private static final double epsilon = 0.000001;
	private ChangeEvent event = new ChangeEvent(this);

	public LogarithmicRangeModel(double base_, double factor_, double value_, 
			double minimum_, double maximum_) {
		this.base = base_;
		this.factor = factor_;
		this.value = value_;
		this.minimum = minimum_;
		this.maximum = maximum_;
	}
	
	/* (non-Javadoc)
	 * @see net.kaspervandenberg.apps.rpg.ui.ILogarithmicRangeModel#addChangeListener(javax.swing.event.ChangeListener)
	 */
	@Override
	public void addChangeListener(ChangeListener x) {
		this.listeners.add(ChangeListener.class, x);
	}

	/* (non-Javadoc)
	 * @see net.kaspervandenberg.apps.rpg.ui.ILogarithmicRangeModel#removeChangeListener(javax.swing.event.ChangeListener)
	 */
	@Override
	public void removeChangeListener(ChangeListener x) {
		this.listeners.remove(ChangeListener.class, x);
	}

	/* (non-Javadoc)
	 * @see net.kaspervandenberg.apps.rpg.ui.ILogarithmicRangeModel#getExtent()
	 */
	@Override
	public int getExtent() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see net.kaspervandenberg.apps.rpg.ui.ILogarithmicRangeModel#setExtent(int)
	 */
	@Override
	public void setExtent(int newExtent) {
		// Do nothing
	}

	/* (non-Javadoc)
	 * @see net.kaspervandenberg.apps.rpg.ui.ILogarithmicRangeModel#getMaximum()
	 */
	@Override
	public int getMaximum() {
		return this.toLog(this.maximum);
	}
	
	/* (non-Javadoc)
	 * @see net.kaspervandenberg.apps.rpg.ui.ILogarithmicRangeModel#getUnconvertedMaximum()
	 */
	public double getUnconvertedMaximum() {
		return this.maximum;
	}

	/* (non-Javadoc)
	 * @see net.kaspervandenberg.apps.rpg.ui.ILogarithmicRangeModel#setMaximum(int)
	 */
	@Override
	public void setMaximum(int v) {
		this.setUnconvertedMaximum(this.toValue(v));
	}
	
	/* (non-Javadoc)
	 * @see net.kaspervandenberg.apps.rpg.ui.ILogarithmicRangeModel#setUnconvertedMaximum(double)
	 */
	public void setUnconvertedMaximum(double newMaximum) {
		if (this.internSetMaximum(newMaximum)) {
			this.enforceValueInRange();
			this.fireEvent();
		}		
	}

	private boolean internSetMaximum(double newMaximum) {
		boolean change = !LogarithmicRangeModel.equals(this.maximum, newMaximum);
		if (change) {
			this.maximum = newMaximum;
		} // if change
		return change;
	}

	/* (non-Javadoc)
	 * @see net.kaspervandenberg.apps.rpg.ui.ILogarithmicRangeModel#getMinimum()
	 */
	@Override
	public int getMinimum() {
		return this.toLog(this.minimum);
	}

	/* (non-Javadoc)
	 * @see net.kaspervandenberg.apps.rpg.ui.ILogarithmicRangeModel#setMinimum(int)
	 */
	@Override
	public void setMinimum(int v) {
		this.setUnconvertedMinimum(this.toValue(v));
	}
	
	/* (non-Javadoc)
	 * @see net.kaspervandenberg.apps.rpg.ui.ILogarithmicRangeModel#setUnconvertedMinimum(double)
	 */
	public void setUnconvertedMinimum(double newMinimum) {
		if (this.internSetMinimum(newMinimum)) {
			this.enforceValueInRange();
			this.fireEvent();
		}
	}

	private boolean internSetMinimum(double newMinimum) {
		boolean change = !LogarithmicRangeModel.equals(this.minimum, newMinimum);
		if (change) {
			this.minimum = newMinimum;
		} // if change
		return change;
	}

	/* (non-Javadoc)
	 * @see net.kaspervandenberg.apps.rpg.ui.ILogarithmicRangeModel#getValue()
	 */
	@Override
	public int getValue() {
		return this.toLog(this.value);
	}

	/* (non-Javadoc)
	 * @see net.kaspervandenberg.apps.rpg.ui.ILogarithmicRangeModel#setValue(int)
	 */
	@Override
	public void setValue(int v) {
		this.setUnconvertedValue(this.toValue(v));
	}

	@Override
	public void setUnconvertedValue(double newValue) {
		if (this.internSetValue(newValue)) {
			this.enforceValueInRange();
			this.fireEvent();
		}
	}

	private boolean internSetValue(double newValue) {
		boolean change = !LogarithmicRangeModel.equals(this.value, newValue);
		if (change) {
			this.value = newValue;
			System.err.println("Value:" + (Double)newValue);
		} // if change
		return change;
	}
	
	/* (non-Javadoc)
	 * @see net.kaspervandenberg.apps.rpg.ui.ILogarithmicRangeModel#getBase()
	 */
	public double getBase() {
		return this.base;
	}
	
	/* (non-Javadoc)
	 * @see net.kaspervandenberg.apps.rpg.ui.ILogarithmicRangeModel#setBase(double)
	 */
	public void setBase(double newBase) {
		boolean change = !LogarithmicRangeModel.equals(this.base, newBase);
		if (change && (newBase > 0.0)) {
			this.base = newBase;
			this.fireEvent();
		} // if change
	}

	/* (non-Javadoc)
	 * @see net.kaspervandenberg.apps.rpg.ui.ILogarithmicRangeModel#getFactor()
	 */
	public double getFactor() {
		return this.factor;
	}
	
	/* (non-Javadoc)
	 * @see net.kaspervandenberg.apps.rpg.ui.ILogarithmicRangeModel#setFactor(double)
	 */
	public void setFactor(double newFactor) {
		boolean change = !LogarithmicRangeModel.equals(this.factor, newFactor);
		if (change) {
			this.factor = newFactor;
			this.fireEvent();
		} // if change
	}

	/* (non-Javadoc)
	 * @see net.kaspervandenberg.apps.rpg.ui.ILogarithmicRangeModel#getValueIsAdjusting()
	 */
	@Override
	public boolean getValueIsAdjusting() {
		return this.isAdjusting;
	}

	/* (non-Javadoc)
	 * @see net.kaspervandenberg.apps.rpg.ui.ILogarithmicRangeModel#setValueIsAdjusting(boolean)
	 */
	@Override
	public void setValueIsAdjusting(boolean b) {
		if (this.internSetIsAdjusting(b)) {
			this.fireEvent();
		}
	}
	
	private boolean internSetIsAdjusting(boolean b) {
		boolean change = (this.isAdjusting != b);
		this.isAdjusting = b;
		return change;
	}

	/* (non-Javadoc)
	 * @see net.kaspervandenberg.apps.rpg.ui.ILogarithmicRangeModel#setRangeProperties(int, int, int, int, boolean)
	 */
	@Override
	public void setRangeProperties(int value, int extent, int min, int max,
			boolean adjusting) {
		boolean changed = false;
		changed |= this.internSetValue(this.toValue(value));
		changed |= this.internSetMinimum(this.toValue(min));
		changed |= this.internSetMaximum(this.toValue(max));
		changed |= this.internSetIsAdjusting(adjusting);
		
		if(changed) {
			this.enforceValueInRange();
			this.fireEvent();			
		}
	}

	private void fireEvent() {
		ChangeListener[] listeners = this.listeners.getListeners(ChangeListener.class);
		for (ChangeListener listener : listeners) {
			listener.stateChanged(this.event);
		} // foreach
	}

	private void enforceValueInRange() {
		if (this.minimum < LogarithmicRangeModel.epsilon) {
			this.minimum = LogarithmicRangeModel.epsilon;
		}
		if (this.minimum > this.maximum) {
			this.minimum = this.maximum;
		}
		if (this.value < this.minimum) {
			this.value = this.minimum;
		}
		if (this.value > this.maximum) {
			this.value = this.maximum;
		}
	}

	/* (non-Javadoc)
	 * @see net.kaspervandenberg.apps.rpg.ui.ILogarithmicRangeModel#toLog(double)
	 */
	public int toLog(double value) {
		int result = (int) Math.round((Math.log(value)  / Math.log(this.base)) * this.factor);
		assert(LogarithmicRangeModel.equals(value, this.toValue(result)));
		return result;
	}
	
	/* (non-Javadoc)
	 * @see net.kaspervandenberg.apps.rpg.ui.ILogarithmicRangeModel#toValue(int)
	 */
	public double toValue(int log) {
		double result = Math.pow(this.base, (log / this.factor));
		return result;
	}
	
	private static boolean equals(double a, double b) {
		if (a == b) return true;
		return ((Math.abs(a-b) < LogarithmicRangeModel.epsilon));
	}

	@Override
	public double getUnconvertedMinimum() {
		return this.minimum;
	}

	@Override
	public double getUnconvertedValue() {
		return this.value;
	}

}
