package server.remote;

import java.util.List;

public interface IRemoteList<T> {

	public abstract void add(T e);

	public abstract int size();

	public abstract void copie(List<T> l);

}