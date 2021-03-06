package org.hisrc.jsonix.analysis;

import org.jvnet.jaxb2_commons.xml.bind.model.MPackageInfo;

public abstract class InfoVertex<T, C extends T> {

	public abstract MPackageInfo getPackageInfo();

	public abstract <V> V accept(InfoVertexVisitor<T, C, V> visitor);

}
