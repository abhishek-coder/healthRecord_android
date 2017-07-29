package health.tpg.com.health.enums;


import health.tpg.com.health.util.AppConstants;

public enum FragmentTag {

    //meeting room fragments
    CalenderFragment(AppConstants.Modules.MEETING_ROOM_MODULE);

    // for further module fragments please specify here.

    private String module;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    FragmentTag(String module) {
        this.module = module;
    }

}
