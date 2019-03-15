package Service;

import Domain.Pacient;
import Repository.RepoPacient;

import java.util.List;

public class ServicePacient {

    private RepoPacient repository;

    public ServicePacient(RepoPacient repository) {
        this.repository = repository;
    }

    public void addOrUpdate(int id, String CNP, String firstName, String lastName, String date, String regDate) {
        Pacient p = repository.findById(id);
        if (p != null) {
            if (CNP.isEmpty())
                CNP = p.getCNP();
            if (firstName.isEmpty())
                firstName = p.getFirstName();
            if (lastName.isEmpty())
                lastName = p.getLastName();
            if (date.isEmpty())
                date = p.getBirthDate();
            if (regDate.isEmpty())
                regDate = p.getRegDate();
        }
        Pacient pp = new Pacient(id, CNP, firstName, lastName, date, regDate);
        repository.add(pp);
    }

    public void remove (int id) {
        repository.remove(id);
    }

    public List<Pacient> showPacients() {
        return repository.showPacients();
    }
}
