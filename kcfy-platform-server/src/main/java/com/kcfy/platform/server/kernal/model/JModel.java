package com.kcfy.platform.server.kernal.model;

import java.io.Serializable;

import com.kcfy.platform.server.cache.proext.PropertyExtendable;

/**
 * indicate all implementations can be serialized.
 *  its strongly recommended any implementation must involve primary key. 
 * @author Administrator
 *
 */
public interface JModel extends Serializable  , Cloneable ,PropertyExtendable{

}
