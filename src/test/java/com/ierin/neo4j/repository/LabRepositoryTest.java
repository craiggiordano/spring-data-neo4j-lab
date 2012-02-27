package com.ierin.neo4j.repository;

import com.ierin.neo4j.domain.Lab;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/domain-test-context.xml"})
@Transactional
public class LabRepositoryTest {

    @Autowired
    private LabRepository labRepository;

    @Before
    public void setUp() throws Exception {
        Lab lab_1 = new Lab(SINGLE_1, FOO_1, BAR_1, SINGLE_LONG_1, FOO_LONG_1, BAR_LONG_1).persist();
        Lab lab_2 = new Lab(SINGLE_2, FOO_2, BAR_2, SINGLE_LONG_2, FOO_LONG_2, BAR_LONG_2).persist();
    }

    @Test
    public void canPersistAndFindAllLabs() throws Exception {

        EndResult<Lab> labs = labRepository.findAll();
        assertNotNull("Labs not found.", labs);
        Iterator<Lab> iterator = labs.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            Lab lab = iterator.next();
            count++;
        }
        assertEquals("Wrong number of labs found.", 2, count);
    }

    @Test
    public void canFindLabBySingleString() throws Exception {
        List<Lab> labs = labRepository.findLabBySingleString(SINGLE_1);
        validate(labs);
    }

    @Test
    public void canFindLabByMultipleString()  throws Exception {
        String searchKey = "foo:" + FOO_1 + " AND bar:" + BAR_1;
        List<Lab> labs = labRepository.findLabByMultipleString(searchKey);
        validate(labs);
    }

    @Test
    public void canFindLabBySingleLong() throws Exception {
        List<Lab> labs = labRepository.findLabBySingleLong(SINGLE_LONG_1);
        validate(labs);
    }

    @Test
    public void canFindLabByMultipleLong() throws Exception {
        String searchKey = "fooLong:" + FOO_LONG_1 + " AND barLong:" + BAR_LONG_2;
        List<Lab> labs = labRepository.findLabByMultipleLong(searchKey);
        validate(labs);
    }

    private void validate(List<Lab> labs) {
        assertNotNull("Labs not found.", labs);
        assertEquals("Wrong number of labs found.", 1, labs.size());
        Lab lab = labs.get(0);
        assertEquals("Wrong single.", SINGLE_1, lab.getSingle());
        assertEquals("Wrong foo.", FOO_1, lab.getFoo());
        assertEquals("Wrong bar.", BAR_1, lab.getBar());
        assertEquals("Wrong single long.", SINGLE_LONG_1, lab.getSingleLong());
        assertEquals("Wrong foo long.", FOO_LONG_1, lab.getFooLong());
        assertEquals("Wrong bar long.", BAR_LONG_1, lab.getBarLong());
    }

    private static final String SINGLE_1 = "single_1";
    private static final String SINGLE_2 = "single_2";

    private static final String FOO_1 = "foo_1";
    private static final String BAR_1 = "bar_1";
    private static final String FOO_2 = "foo_2";
    private static final String BAR_2 = "bar_2";

    private static final Long SINGLE_LONG_1 = new Long(0);
    private static final Long SINGLE_LONG_2 = new Long(111);

    private static final Long FOO_LONG_1 = new Long(222);
    private static final Long BAR_LONG_1 = new Long(333);
    private static final Long FOO_LONG_2 = new Long(444);
    private static final Long BAR_LONG_2 = new Long(555);
}
