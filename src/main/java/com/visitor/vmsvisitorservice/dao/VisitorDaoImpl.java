
package com.visitor.vmsvisitorservice.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.visitor.vmsvisitorservice.model.Visitor;

@Repository
@Transactional
public class VisitorDaoImpl implements IVisitorDao {

	private static final Logger log = LoggerFactory.getLogger(VisitorDaoImpl.class);

	@PersistenceContext
	private EntityManager entityManger;

	@Override
	public List<Visitor> visitorsList() {
		CriteriaBuilder cb = entityManger.getCriteriaBuilder();
		CriteriaQuery<Visitor> cq = cb.createQuery(Visitor.class);
		Root<Visitor> users = cq.from(Visitor.class);
		CriteriaQuery<Visitor> select = cq.select(users);
		TypedQuery<Visitor> tq = entityManger.createQuery(select);
		List<Visitor> visitors_List = tq.getResultList();
		return visitors_List;
	}

	@Override
	public Visitor findById(long id) {

		CriteriaBuilder cb = entityManger.getCriteriaBuilder();
		CriteriaQuery<Visitor> cq = cb.createQuery(Visitor.class);
		Root<Visitor> user = cq.from(Visitor.class);
		Predicate userIdPredicate = cb.equal(user.get("id"), id);
		cq.where(userIdPredicate);
		TypedQuery<Visitor> query = entityManger.createQuery(cq);
		return query.getSingleResult();
	}

	@Override
	public Visitor findByName(String name) {
		CriteriaBuilder cb = entityManger.getCriteriaBuilder();
		CriteriaQuery<Visitor> cq = cb.createQuery(Visitor.class);
		Root<Visitor> user = cq.from(Visitor.class);
		Predicate userNamePredicate = cb.like(user.get("name"), name);
		cq.where(userNamePredicate);
		TypedQuery<Visitor> query = entityManger.createQuery(cq);
		return query.getSingleResult();
	}

	@Override
	public void updateVisitorById(Visitor visitor, long id) {
		CriteriaBuilder cb = entityManger.getCriteriaBuilder();
		CriteriaUpdate<Visitor> update = cb.createCriteriaUpdate(Visitor.class);
		Root<Visitor> user = update.from(Visitor.class);
		update.set("contactPersonEmail", visitor.getContactPersonEmail());
		update.set("contactPersonName", visitor.getContactPersonName());
		update.set("email", visitor.getEmail());
		update.set("idProof", visitor.getIdProof());
		update.set("mobileNo", visitor.getMobileNo());
		update.set("name", visitor.getName());
		update.set("reasonForVisit", visitor.getReasonForVisit());
		update.set("contactPersonMobileNo", visitor.getContactPersonMobileNo());
		update.set("idProofType", visitor.getIdProofType());
		// update.set("id", visitor.getId());
		update.where(cb.equal(user.get("id"), id));
		entityManger.createQuery(update).executeUpdate();
	}

	@Override
	public void deleteVisitorById(long id) {
		CriteriaBuilder cb = entityManger.getCriteriaBuilder();
		CriteriaDelete<Visitor> delete = cb.createCriteriaDelete(Visitor.class);
		Root<Visitor> user = delete.from(Visitor.class);
		delete.where(cb.equal(user.get("id"), id));
		entityManger.createQuery(delete).executeUpdate();
	}

	@Override
	public void updateVisitor(Visitor v) {
		entityManger.merge(v);
	}

	@Override
	public List<Visitor> searchVisitor(Visitor visitor) {

		CriteriaBuilder cb = entityManger.getCriteriaBuilder();
		CriteriaQuery<Visitor> cq = cb.createQuery(Visitor.class);
		Root<Visitor> user = cq.from(Visitor.class);

		List<Predicate> predicates = new ArrayList<>();

		if (visitor.getName() != null) {
			predicates.add(cb.like(user.get("name"), visitor.getName()));
		}

		if (visitor.getEmail() != null) {
			predicates.add(cb.like(user.get("email"), visitor.getEmail()));
		}

		if (visitor.getContactPersonName() != null) {
			predicates.add(cb.like(user.get("contactPersonName"), visitor.getContactPersonName()));
		}

		if (visitor.getContactPersonEmail() != null) {
			predicates.add(cb.like(user.get("contactPersonEmail"), visitor.getContactPersonEmail()));
		}

		if (visitor.getVisitorType() != null) {
			predicates.add(cb.like(user.get("visitorType"), visitor.getVisitorType()));
		}

		if (visitor.getIdProof() != null) {
			predicates.add(cb.like(user.get("idProof"), visitor.getIdProof()));
		}

		cq.where(predicates.stream().iterator().next());
		TypedQuery<Visitor> query = entityManger.createQuery(cq);
		log.debug("Query: " + query);
		return query.getResultList();
	}

}
