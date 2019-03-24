import enumerated.Outcome;

public interface Competitor<T extends Competitor<T>> {
  Outcome compete(T competitor);
}