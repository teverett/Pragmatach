package com.khubla.pragmatach.plugin.adminapp;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.Route.HttpMethod;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.router.Router;

/**
 * @author tome
 */
@Controller(name = "pragmatachShowRouteCacheController")
@View(view = "pragmatach/admin/routecache.html")
public class ShowRouteCacheController extends AbstractAdminController {
   private long size;
   private long totalHits;
   private long totalRequests;
   private float hitRatio;

   @Route(uri = "/pragmatach/admin/routecache/clear", method = HttpMethod.post)
   public Response clearCache() throws PragmatachException {
      Router.getRoutecache().clear();
      return super.render();
   }

   public float getHitRatio() {
      return hitRatio;
   }

   public long getSize() {
      return size;
   }

   public long getTotalHits() {
      return totalHits;
   }

   public long getTotalRequests() {
      return totalRequests;
   }

   @Route(uri = "/pragmatach/admin/routecache")
   public Response render() throws PragmatachException {
      size = Router.getRoutecache().size();
      totalHits = Router.getRoutecache().getTotalHits();
      totalRequests = Router.getRoutecache().getTotalRequests();
      if (0 != totalRequests) {
         hitRatio = 100 * ((float) totalHits / (float) totalRequests);
      } else {
         hitRatio = 0;
      }
      return super.render();
   }

   public void setHitRatio(float hitRatio) {
      this.hitRatio = hitRatio;
   }

   public void setSize(long size) {
      this.size = size;
   }

   public void setTotalHits(long totalHits) {
      this.totalHits = totalHits;
   }

   public void setTotalRequests(long totalRequests) {
      this.totalRequests = totalRequests;
   }
}
