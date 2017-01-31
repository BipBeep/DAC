/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.person;

import fr.ensimag.contacts.Person;
import fr.ensimag.contacts.stateless.PersonFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author duclaur
 */
@Named(value = "listAllPersons")
@RequestScoped
public class ListAll {

    @EJB
    private PersonFacadeLocal personFacade;

    private List<Person> persons = null;

    public List<Person> getPersons() {
        if (persons == null) {
            persons = personFacade.findAll();
        }
        return persons;
    }

    /**
     * Creates a new instance of ListAll
     */
    public ListAll() {
    }

}
