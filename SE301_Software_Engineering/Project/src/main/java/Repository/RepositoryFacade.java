package Repository;

import ActivityManagement.Activity;
import ActivityManagement.SpecialActivity;
import RequestManagement.ActivityRequest;
import RequestManagement.MembershipRequest;
import RequestManagement.SpecialActivityRequest;
import RequestManagement.VIPRequest;
import UserManagement.*;
import java.io.Serializable;
import java.util.ArrayList;

public class RepositoryFacade implements Serializable {

    public RepositoryFacade() {
    }

    public void insertInstructor(Instructor instructor) {
        DBTransactions.getInstance().instructorTransaction('i', instructor);
    }

    public void updateInstructor(Instructor instructor) {
        DBTransactions.getInstance().instructorTransaction('u', instructor);
    }

    public void deleteInstructor(Instructor instructor) {
        DBTransactions.getInstance().instructorTransaction('d', instructor);
    }

    public void insertMember(Member member) {
        DBTransactions.getInstance().memberTransaction('i', member);
    }

    public void updateMember(Member member) {
        DBTransactions.getInstance().memberTransaction('u', member);
    }

    public void deleteMember(Member member) {
        DBTransactions.getInstance().memberTransaction('d', member);
    }

    public void insertRegistrar(Registrar registrar) {
        DBTransactions.getInstance().registrarTransaction('i', registrar);
    }

    public void deleteRegistrar(Registrar registrar) {
        DBTransactions.getInstance().registrarTransaction('d', registrar);
    }

    public void updateRegistrar(Registrar registrar) {
        DBTransactions.getInstance().registrarTransaction('u', registrar);
    }

    public void insertScheduler(Scheduler scheduler) {
        DBTransactions.getInstance().schedulerTransaction('i', scheduler);
    }

    public void deleteScheduler(Scheduler scheduler) {
        DBTransactions.getInstance().schedulerTransaction('d', scheduler);
    }

    public void updateScheduler(Scheduler scheduler) {
        DBTransactions.getInstance().schedulerTransaction('u', scheduler);
    }

    public void insertCourt(Court court) {
        DBTransactions.getInstance().courtTransaction('i', court);
    }

    public void deleteCourt(Court court) {
        DBTransactions.getInstance().courtTransaction('d', court);
    }

    public void updateCourt(Court court) {
        DBTransactions.getInstance().courtTransaction('u', court);
    }

    public void insertActivity(Activity activity) {
        DBTransactions.getInstance().insertActivity(activity);
    }

    public void deleteActivity(Activity activity) {
        DBTransactions.getInstance().deleteActivity(activity);
    }

    public void updateActivity(Activity oldActivity, Activity newActivity) {
        DBTransactions.getInstance().updateActivity(oldActivity, newActivity);
    }

    public void insertActivityRequest(ActivityRequest activityRequest) {
        DBTransactions.getInstance().activityRequestTransaction('i', activityRequest);
    }

    public void deleteActivityRequest(ActivityRequest activityRequest) {
        DBTransactions.getInstance().activityRequestTransaction('d', activityRequest);
    }

    public void insertVIPRequest(VIPRequest vipRequest) {
        DBTransactions.getInstance().vipRequestTransaction('i', vipRequest);
    }

    public void deleteVIPRequest(VIPRequest vipRequest) {
        DBTransactions.getInstance().vipRequestTransaction('d', vipRequest);
    }

    public void insertSpecialActivityRequest(SpecialActivityRequest specialActivityRequest) {
        DBTransactions.getInstance().specialActivityRequestTransaction('i', specialActivityRequest);
    }

    public void deleteSpecialActivityRequest(SpecialActivityRequest specialActivityRequest) {
        DBTransactions.getInstance().specialActivityRequestTransaction('d', specialActivityRequest);
    }

    public void insertMembershipRequest(MembershipRequest membershipRequest) {
        DBTransactions.getInstance().membershipRequestTransaction('i', membershipRequest);
    }

    public void deleteMembershipRequest(MembershipRequest membershipRequest) {
        DBTransactions.getInstance().membershipRequestTransaction('d', membershipRequest);
    }

    public ArrayList<ActivityRequest> getActivityRequests(int id) {
        return DBTransactions.getInstance().getActivityRequests(id);
    }

    public ArrayList<SpecialActivityRequest> getSpecialActivityRequests(int id) {
        return DBTransactions.getInstance().getSpecialActivityRequests(id);
    }

    public ArrayList<VIPRequest> getVIPRequests() {
        return DBTransactions.getInstance().getVIPRequests();
    }

    public ArrayList<MembershipRequest> getMembershipRequests() {
        return DBTransactions.getInstance().getMembershipRequests();
    }

    public void memberJoinsActivity(Member member, Activity activity) {
        DBTransactions.getInstance().memberJoinsActivityTransactions('i', member, activity);
    }

    public void memberQuitsActivity(Member member, Activity activity) {
        DBTransactions.getInstance().memberJoinsActivityTransactions('d', member, activity);
    }

    public void vipmemberJoinsSpecialActivity(VIPMember vipmember, SpecialActivity specialActivity) {
        DBTransactions.getInstance().vipmemberJoinsSpecialActivityTransactions('i', vipmember, specialActivity);
    }

    public void vipmemberQuitsSpecialActivity(VIPMember vipmember, SpecialActivity specialActivity) {
        DBTransactions.getInstance().vipmemberJoinsSpecialActivityTransactions('d', vipmember, specialActivity);
    }

    public void memberBecomesVIPMember(Member member) {
        DBTransactions.getInstance().becomeVIPMemberTransaction('i', member);
    }

    public void memberQuitsVIPMember(Member member) {
        DBTransactions.getInstance().becomeVIPMemberTransaction('d', member);
    }

    public void vipmemberReservesCourt(VIPMember vipmember, Court court) {
        DBTransactions.getInstance().vipmemberReservesCourtTransactions('i', vipmember, court);
    }

    public void vipmemberReservationOfCourtEnds(VIPMember vipmember, Court court) {
        DBTransactions.getInstance().vipmemberReservesCourtTransactions('d', vipmember, court);
    }

    public ArrayList<Court> getACourtSchedule(Court court) {
        return DBTransactions.getInstance().getCourtSchedule(court);
    }

    public Member getMember(int id) {
        return DBTransactions.getInstance().getMember(id);
    }

    public Instructor getInstructor(int id) {
        return DBTransactions.getInstance().getInstructor(id);
    }

    public Registrar getRegistrar(int id) {
        return DBTransactions.getInstance().getRegistrar(id);
    }

    public Scheduler getScheduler(int id) {
        return DBTransactions.getInstance().getScheduler(id);
    }

    public VIPMember getVipMember(int id) {
        return DBTransactions.getInstance().getVIPMember(id);
    }

    public ArrayList<Court> getAllCourts() {
        return DBTransactions.getInstance().getAllCourts();
    }

    public ArrayList<Activity> getAllActivities() {
        return DBTransactions.getInstance().getAllActivity();
    }

    public ArrayList<SpecialActivity> getAllSpecialActivities() {
        return DBTransactions.getInstance().getAllSpecialActivity();
    }

    public ArrayList<Instructor> getAllInstructors() {
        return DBTransactions.getInstance().getAllInstructors();
    }

    public ArrayList<Activity> getMemberActivities(Member member) {
        return DBTransactions.getInstance().getMemberActivities(member);
    }

    public ArrayList<SpecialActivity> getVIPMemberSpecialActvities(VIPMember vipmember) {
        return DBTransactions.getInstance().getVIPMemberSpecialActivities(vipmember);
    }
    
    public int getNumberOfMembers() {
        return DBTransactions.getInstance().getNumberOfMembers();
    }
    
    public int getNumberOfInstructors() {
        return DBTransactions.getInstance().getNumberOfInstructors();
    }
    
    public int getNumberOfSchedulers() {
        return DBTransactions.getInstance().getNumberOfSchedulers();
    }
    
    public boolean isMemberExistent(Member m) {
        return DBTransactions.getInstance().isMemberExistent(m);
    }
    
    public boolean isInstructorExistent(Instructor i) {
        return DBTransactions.getInstance().isInstructorExistent(i);
    }
    
    public boolean isSchedulerExistent(Scheduler s) {
        return DBTransactions.getInstance().isSchedulerExistent(s);
    }
    
    public boolean checkPasswordOfMember(int id, String password) {
        return DBTransactions.getInstance().checkPasswordOfMember(id, password);
    }
    
    public boolean checkPasswordOfScheduler(int id, String password) {
        return DBTransactions.getInstance().checkPasswordOfScheduler(id, password);
    }
    
    public boolean checkPasswordOfInstructor(int id, String password) {
        return DBTransactions.getInstance().checkPasswordOfInstructor(id, password);
    }
    
    public boolean checkPasswordOfRegistrar(int id, String password) {
        return DBTransactions.getInstance().checkPasswordOfRegistrar(id, password);
    }
    
    public boolean isActivityExist(Activity activity){
        return DBTransactions.getInstance().activityExist(activity);
    }
    
    public boolean isActivityRequestExist(ActivityRequest ar) {
        return DBTransactions.getInstance().isActivityRequestExist(ar);
    }
    
    public boolean isVIPRequestExist(VIPRequest vr) {
        return DBTransactions.getInstance().isVIPRequestExist(vr);
    }
    
    public boolean isMembershipRequestExist(MembershipRequest mr) {
        return DBTransactions.getInstance().isMembershipRequestExist(mr);
    }
    
    public boolean isSpecialActivityRequestExist(SpecialActivityRequest sar) {
        return DBTransactions.getInstance().isSpecialActivityRequestExist(sar);
    }
    
    public boolean isMemberJoinedActivity(Member m, Activity a) {
        return DBTransactions.getInstance().isMemberJoinedActivity(m, a);
    }
    
    public boolean isVIPMemberJoinedSpecialActivity(VIPMember vm, SpecialActivity sa) {
        return DBTransactions.getInstance().isMemberJoinedActivity(vm, sa);
    }
    
    public boolean isVIPMember(int id) {
        return DBTransactions.getInstance().vipMemberExist(id);
    }
}
