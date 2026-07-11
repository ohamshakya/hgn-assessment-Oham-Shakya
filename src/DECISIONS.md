## System Design

I implemented the Alert service, which is responsible for receiving SOS alerts from devices and storing them in the database.

### Request Flow

1. Groups are registered with the members
2. Orders are placed according to the groups
3. Device are registered.
4. A device is assigned through order and group
5. An alert is sent from the device and is saved to the database.


The current implementation ends after storing the alert. In a complete system, another service would consume the stored alerts and handle coordinator assignment, notifications, and escalation.

### Layers

- API Layer – Accepts incoming alert requests and validates them.
- Service Layer – Contains the business logic for creating alerts.
- Repository/Data Access Layer – Handles communication with the database.
- Database – Stores alert records.

## Data Model

The primary entity I implemented is the **Alert** model.

### Alert

The Alert model stores every SOS event received from a device. It includes fields such as:

- `uuidCode` - uniquely generated
- `deviceId`- Identifies the device that sends alert.
- `latitude`- coordinates from the device
- `longitude`- coordinates from the device
- `createdAt`- created timestamp from the device
- `updatedAt`- updated timestamp when update
- `status` – Current state of the alert (e.g., pending, acknowledged, resolved).


I designed the Alert model to capture all information needed to record an incoming event while remaining independent of business workflows such as assignment or escalation.

#### Device ambiguity

When a new order is created, its status is NOT_ASSIGNED.
Before assigning a device, the system checks:
If the order is already ASSIGNED, it throws an exception.
If the device is already ASSIGNED, it throws an exception.
Only when both the order and the device are available does the system create the assignment and update their status.

This means that at any given time:

One order can have only one assigned device.
One device can be assigned to only one active order.

Therefore, when an alert comes from a device, the system can identify the correct order through the AssignDevice record.



