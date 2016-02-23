/**********************************************************************
 * Cargo
 *
 * Copyright (c) 2012: NDS Limited
 *
 * P R O P R I E T A R Y & C O N F I D E N T I A L
 *
 * The copyright of this code and related documentation together with any
 * other associated intellectual property rights are vested in NDS Limited
 * and may not be used except in accordance with the terms of the license
 * that you have entered into with NDS Limited. Use of this material
 * without an express license from NDS Limited shall be an infringement of
 * copyright and any other intellectual property rights that may be
 * incorporated with this material.
 **********************************************************************/


package dash.cd.youtube;

/**
 * @author DarshanR
 * @created Jun 21, 2012
 */
public class Cargo {

    private int serviceType;

    private int subServiceType;

    private Object payload;

    private int errorCode;

    /**
     * @return the serviceType
     */
    public int getServiceType() {
        return serviceType;
    }

    /**
     * @param serviceType the serviceType to set
     */
    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * @return the subServiceType
     */
    public int getSubServiceType() {
        return subServiceType;
    }

    /**
     * @param subServiceType the subServiceType to set
     */
    public void setSubServiceType(int subServiceType) {
        this.subServiceType = subServiceType;
    }

    /**
     * @return the payload
     */
    public Object getPayload() {
        return payload;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(Object payload) {
        this.payload = payload;
    }

    /**
     * @return the errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

}
