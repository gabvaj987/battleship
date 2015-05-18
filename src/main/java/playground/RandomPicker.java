package playground;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RandomPicker<T> implements Iterable<T> {
	private final List<T> listToChooseFrom;
	private final Random random = new Random();

	public RandomPicker(final List<T> listToChooseFrom) {
		this.listToChooseFrom = new ArrayList<>(listToChooseFrom);
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			@Override
			public boolean hasNext() {
				return listToChooseFrom.size() > 0;
			}

			@Override
			public T next() {
				final int removeAt = random.nextInt(listToChooseFrom.size());
				return listToChooseFrom.remove(removeAt);
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
}
