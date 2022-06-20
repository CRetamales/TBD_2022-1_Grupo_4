package cl.tbd.backendayni.repositories;

import cl.tbd.backendayni.models.Region;
import org.sql2o.Sql2o;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;

public class RegionRepositoryImp implements RegionRepository {
    @Autowired
    private Sql2o sql2o;

    @Override
    public int countRegiones() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM region";
        try (Connection conn = sql2o.open()) {
            total = conn.createQuery(sql).executeScalar(Integer.class);
            return total;
        }
    }

    @Override
    public int newId() {
        int id = 0;
        String sql = "SELECT MAX(id) FROM region";
        try (Connection conn = sql2o.open()) {
            id = conn.createQuery(sql).executeScalar(Integer.class);
            return id;
        }
    }

    @Override
    public List<Region> getAll() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM region ORDER BY Region.id ASC")
                    .executeAndFetch(Region.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Region> showRegionById(long id) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM region WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetch(Region.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Region createRegion(Region region) {
        try (Connection conn = sql2o.open()) {
            String sql = "INSERT INTO region (id_region, nom_region, geom) VALUES (:id_region2, :nombre2, :geom2)";
            conn.createQuery(sql)
                    .addParameter("id_region2", region.getId_region())
                    .addParameter("nombre2", region.getNom_region())
                    .addParameter("geom2", region.getGeom())
                    .executeUpdate();
            region.setId(newId());
            return region;
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getLocalizedMessage() + "No se pudo crear la region");
            return null;
        }
    }

    @Override
    public void deleteRegionById(long id) {
        try (Connection conn = sql2o.open()) {
            String sql = "DELETE FROM region WHERE id = :id";
            conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getLocalizedMessage() + "No se pudo eliminar la region");
        }

    }

    @Override
    public void updateRegion(Region region) {
        try (Connection conn = sql2o.open()) {
            String sql = "UPDATE region SET id_region = :id_region2, nom_region = :nombre2, geom = :geom2 WHERE id = :id";
            conn.createQuery(sql)
                    .addParameter("id_region2", region.getId_region())
                    .addParameter("nombre2", region.getNom_region())
                    .addParameter("geom2", region.getGeom())
                    .addParameter("id", region.getId())
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getLocalizedMessage() + "No se pudo actualizar la region");
        }

    }

}
