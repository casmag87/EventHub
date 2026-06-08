package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import model.DTO.IncidentDTO;
import model.Incident;
import state.Event;
import state.State;
import state.StateMachine;

import java.util.List;

@ApplicationScoped
public class IncidentService {

    @Inject
    EntityManager em;

    @Inject
    StateMachine stateMachine;

    @Transactional
    public IncidentDTO createIncident(IncidentDTO incidentDTO, String userId){
        Incident incident = new Incident(incidentDTO);


        incident.setCreatedByUserId(userId);
        incident.setCreatedAt(java.time.LocalDateTime.now());
        incident.setStatus(State.NEW);

        em.persist(incident);


        return new IncidentDTO(incident);
    }

    @Transactional
    public List<IncidentDTO> getAllIncidents() {
        TypedQuery<IncidentDTO> query =
                em.createQuery("Select i FROM Incident i",IncidentDTO.class);
        List<IncidentDTO> result = query.getResultList();

        if(result.isEmpty()){
            throw new EntityNotFoundException("no incidents in database");
        }

        return result;
    }


    @Transactional
    public IncidentDTO getIncident(Long incidentId){

        Incident incident = em.find(Incident.class, incidentId);

        if(incident == null){
            throw new EntityNotFoundException("Incident not found with id: " + incidentId);
        }
        return new IncidentDTO(incident);
    }

    @Transactional
    public IncidentDTO updateAssignedTo(Long incidentId, String newAssignedToUserId) {


        Incident incident = em.find(Incident.class, incidentId);

        if (incident == null) {
            throw new EntityNotFoundException("Incident not found with id: " + incidentId);
        }

        incident.setAssignedToUserId(newAssignedToUserId);

        return new IncidentDTO(incident);
    }

    @Transactional
    public IncidentDTO updateState(Long incidentId, Event event) {

        Incident incident = em.find(Incident.class, incidentId);

        if (incident == null) {
            throw new EntityNotFoundException(
                    "Incident not found with id: " + incidentId);
        }

        State nextState = stateMachine.transition(
                incident.getStatus(),
                event
        );

        incident.setStatus(nextState);

        return new IncidentDTO(incident);
    }
}
