package org.eni.encheres.bo;

import java.time.LocalDateTime;

public class Enchere {
	private LocalDateTime dateEnchere;
	private float montantenchere;
	
	public Enchere(LocalDateTime dateEnchere, float montantenchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantenchere = montantenchere;
	}

	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public float getMontantenchere() {
		return montantenchere;
	}

	public void setMontantenchere(float montantenchere) {
		this.montantenchere = montantenchere;
	}
	
}
