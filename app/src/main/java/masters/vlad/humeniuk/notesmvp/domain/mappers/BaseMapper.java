package masters.vlad.humeniuk.notesmvp.domain.mappers;

public interface BaseMapper<S, O> {

    O map(S instance);

    S mapBack(O instance);
}
