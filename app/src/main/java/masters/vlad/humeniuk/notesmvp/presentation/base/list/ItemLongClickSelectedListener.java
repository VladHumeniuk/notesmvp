package masters.vlad.humeniuk.notesmvp.presentation.base.list;

public interface ItemLongClickSelectedListener<E> extends ItemSelectedListener<E> {

    void onItemLongClick(E entity);
}
