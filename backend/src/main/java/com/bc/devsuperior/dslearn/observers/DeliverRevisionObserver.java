package com.bc.devsuperior.dslearn.observers;

import com.bc.devsuperior.dslearn.entities.Deliver;

public interface DeliverRevisionObserver {

	void onSaveRevision(Deliver deliver);
}
