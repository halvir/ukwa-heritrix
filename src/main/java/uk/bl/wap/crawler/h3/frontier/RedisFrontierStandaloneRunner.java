/**
 * 
 */
package uk.bl.wap.crawler.h3.frontier;

import org.apache.commons.httpclient.URIException;
import org.archive.crawler.prefetch.FrontierPreparer;
import org.archive.modules.CrawlURI;
import org.archive.net.UURIFactory;

/**
 * 
 * Purpose of this class is to run it standalone and check the working.
 * 
 * Needs a suitable Redis instance.
 * 
 * @author Andrew Jackson <Andrew.Jackson@bl.uk>
 *
 */
public class RedisFrontierStandaloneRunner {

    /**
     * @param args
     * @throws URIException
     */
    public static void main(String[] args) throws URIException {

        RedisFrontier rf = new RedisFrontier();
        rf.setRedisEndpoint("redis://localhost:6379");
        rf.connect();

        FrontierPreparer fp = new FrontierPreparer();

        // CrawlURI curi = new CrawlURI(u, pathFromSeed, via, viaContext );
        CrawlURI curi = new CrawlURI(
                UURIFactory.getInstance("http://example.org"));
        curi.setPolitenessDelay(5);

        fp.prepare(curi);

        rf.processScheduleAlways(curi);

        CrawlURI nextCuri = rf.findEligibleURI();

        if (nextCuri.equals(curi)) {
            System.out.println("They match!");
            rf.processFinish(curi);
        }

        nextCuri = rf.findEligibleURI();

    }

}
