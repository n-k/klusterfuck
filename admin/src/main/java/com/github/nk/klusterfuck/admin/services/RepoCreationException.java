package com.github.nk.klusterfuck.admin.services;

/**
 * Created by nipunkumar on 27/05/17.
 */
public class RepoCreationException extends Exception {
	public RepoCreationException(String s, Exception e) {
		super(s, e);
	}
}
