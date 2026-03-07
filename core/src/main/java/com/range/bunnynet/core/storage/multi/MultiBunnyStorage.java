package com.range.bunnynet.core.storage.multi;

import com.range.bunnynet.core.model.GetObjectResponse;
import com.range.bunnynet.core.model.PutObjectRequest;
import com.range.bunnynet.core.model.PutObjectResponse;
import com.range.bunnynet.core.region.Region;

/**
 * High-level facade for performing multi-zone Bunny.net storage operations.
 *
 * <p>This interface provides a simplified API that combines upload,
 * download, and delete operations for scenarios where multiple
 * storage zones and regions are used.</p>
 *
 * <p>For lower-level control, consider using the dedicated
 * uploader, downloader, or deleter components directly.</p>
 *
 * @since 2.1.0
 */
public sealed interface MultiBunnyStorage permits MultiBunnyStorageImpl {

    /**
     * Creates a new {@code MultiBunnyStorage} instance.
     *
     * @param config configuration containing the shared API key;
     *               must not be null
     * @return a configured {@code MultiBunnyStorage} instance
     * @throws IllegalArgumentException if configuration is invalid
     */
    static MultiBunnyStorage create(MultiBunnyNetConfig config) {
        MultiStorageValidator.validateConfig(config);
        return new MultiBunnyStorageImpl(config);
    }

    /**
     * Uploads a file to the specified storage zone and region.
     *
     * @param putObjectRequest the upload request containing object data and metadata;
     *                         must not be null
     * @param storageZoneName  the name of the storage zone;
     *                         must not be null or blank
     * @param storageRegion    the target storage region; must not be null
     * @return a {@link PutObjectResponse} describing the uploaded object
     */
    PutObjectResponse uploadFile(
            PutObjectRequest putObjectRequest,
            String storageZoneName,
            Region storageRegion
    );

    /**
     * Deletes a file from the specified storage zone and region.
     *
     * @param storageZoneName the name of the storage zone;
     *                        must not be null or blank
     * @param key             the object key (path) inside the storage zone;
     *                        must not be null or blank
     * @param storageRegion   the target storage region; must not be null
     */
    void deleteFile(
            String storageZoneName,
            String key,
            Region storageRegion
    );

    /**
     * Downloads a file from the specified storage zone and region.
     *
     * <p><strong>Important:</strong>
     * The returned {@link GetObjectResponse} must be closed after use
     * to release underlying HTTP resources.</p>
     *
     * <pre>{@code
     * try (GetObjectResponse response =
     *          storage.downloadFile("zone", "path/file.png", Region.DE)) {
     *     // read response.getStream()
     * }
     * }</pre>
     *
     * @param storageZoneName the name of the storage zone;
     *                        must not be null or blank
     * @param key             the object key (path) inside the storage zone;
     *                        must not be null or blank
     * @param storageRegion   the target storage region; must not be null
     * @return a {@link GetObjectResponse} containing the object stream and metadata
     */
    GetObjectResponse downloadFile(
            String storageZoneName,
            String key,
            Region storageRegion
    );
}