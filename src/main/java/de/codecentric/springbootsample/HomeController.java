/*
 * Copyright 2016 codecentric AG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.codecentric.springbootsample;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class HomeController {

    @Autowired
    private PersonRepository repository;

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public String getStatus() {

        System.out.println("STILL GOING STRONG "+System.currentTimeMillis());

        return "STILL GOING STRONG "+System.currentTimeMillis();
    }

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public List<DBPerson> getPersons() {
        List<DBPerson> persons = new ArrayList<DBPerson>();

        Iterator<DBPerson> it = repository.findAll().iterator();
        while (it.hasNext()) {
            DBPerson p = it.next();
            System.out.println(p.getId()+" "+p.getName());
            persons.add(p);
        }

        return persons;
    }

}
