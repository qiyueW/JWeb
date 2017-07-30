package system.web.power.interfaces;

import system.web.JWeb;

/**
 *
 * @author wangchunzi
 */
public class UPMDefault implements UPM {

    @Override
    public void ggSuccess(String url, JWeb jw) {
    }

    @Override
    public void ggError(String url, JWeb jw) {
    }

    @Override
    public void dlSuccess(String url, JWeb jw) {
    }

    @Override
    public void dlError(String url, JWeb jw) {
    }

    @Override
    public void sqSuccess(String url, JWeb jw) {
    }

    @Override
    public void sqError(String url, JWeb jw) {
    }

    @Override
    public boolean kl_endByTrue(String url, JWeb jw) {
        return true;
    }

    @Override
    public void klError(String url, JWeb jw) {
    }

    @Override
    public boolean start_endByTrue(String url, JWeb jw) {
        return false;
    }

    @Override
    public void end(String url, JWeb jw) {
    }

    @Override
    public void sqNotLogin(String url, JWeb jw) {
    }

}
