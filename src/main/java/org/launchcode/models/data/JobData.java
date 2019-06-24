package org.launchcode.models.data;

import org.launchcode.models.*;

import java.util.ArrayList;

/**
 * Created by LaunchCode
 */
public class JobData {

    private ArrayList<Job> jobs = new ArrayList<>();
    private static JobData instance;

    private JobFieldData<Employer> employers = new JobFieldData<>();
    private JobFieldData<Location> locations = new JobFieldData<>();
    private JobFieldData<CoreCompetency> coreCompetencies = new JobFieldData<>();
    private JobFieldData<PositionType> positionTypes = new JobFieldData<>();


    private JobData() {
        JobDataImporter.loadData(this);
    }


    public static JobData getInstance() {
        if (instance == null) {
            instance = new JobData();
        }
        return instance;
    }

    public Job findById(int id) {
        for (Job job : jobs) {
            if (job.getId() == id)
                return job;
        }

        return null;
    }

    public ArrayList<Job> findAll() {
        return jobs;
    }


    public ArrayList<Job> findByColumnAndValue(JobFieldType column, String value) {

        ArrayList<Job> matchingJobs = new ArrayList<>();

        for (Job job : jobs) {
            if (getFieldByType(job, column).contains(value))
                matchingJobs.add(job);
        }

        return matchingJobs;
    }


    public ArrayList<Job> findByValue(String value) {

        ArrayList<Job> matchingJobs = new ArrayList<>();

        for (Job job : jobs) {

            if (job.getName().toLowerCase().contains(value)) {
                matchingJobs.add(job);
                continue;
            }

            for (JobFieldType column : JobFieldType.values()) {
                if (column != JobFieldType.ALL && getFieldByType(job, column).contains(value)) {
                    matchingJobs.add(job);
                    break;
                }
            }
        }

        return matchingJobs;
    }


    public void add(Job job) {
        jobs.add(job);
    }


    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }

    public static void setInstance(JobData instance) {
        JobData.instance = instance;
    }

    public void setEmployers(JobFieldData<Employer> employers) {
        this.employers = employers;
    }

    public void setLocations(JobFieldData<Location> locations) {
        this.locations = locations;
    }

    public void setCoreCompetencies(JobFieldData<CoreCompetency> coreCompetencies) {
        this.coreCompetencies = coreCompetencies;
    }

    public void setPositionTypes(JobFieldData<PositionType> positionTypes) {
        this.positionTypes = positionTypes;
    }

    private static JobField getFieldByType(Job job, JobFieldType type) {
        switch(type) {
            case EMPLOYER:
                return job.getEmployer();
            case LOCATION:
                return job.getLocation();
            case CORE_COMPETENCY:
                return job.getCoreCompetency();
            case POSITION_TYPE:
                return job.getPositionType();
        }

        throw new IllegalArgumentException("Cannot get field of type " + type);
    }

    public JobFieldData<Employer> getEmployers() {
        return employers;
    }

    public JobFieldData<Location> getLocations() {
        return locations;
    }

    public JobFieldData<CoreCompetency> getCoreCompetencies() {
        return coreCompetencies;
    }

    public JobFieldData<PositionType> getPositionTypes() {
        return positionTypes;
    }

}
